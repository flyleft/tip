项目中一些服务需要监听其他微服务的启动信息，需要监听到启动后主动向其发请求拉取一些配置等。
可是`eureka-client`并未提供监听其他服务启动的事件，`eureka-server`倒是提供了事件，
可以在自己的`eureka-server`中监听服务启动，监听后发送服务启动信息到kafka这些消息队列，服务监听kafka消息, 
这种方式要依赖消息队列: [源码](https://github.com/flyleft/spring-cloud-base/tree/master/eureka-register-server-event源码)；
或者改造`eureka-client`, 由于需要改的代码不多，修改源码重新打成依赖自己维护不方便，这里通过javassist直接修改jar里的字节码实现。

## 使用javassist修改字节码

 - 查看eureka里源码，有个com.netflix.discovery.shared.Application类，addInstance方法在服务上线或更新，removeInstance方法在服务下线时调用，因此修改这俩方法，实现监听服务上、下线。
 - 由于spring-boot自己实现的类加载机制，以spring-boot的jar形式运行javassist会扫描不到包，要通过insertClassPath添加扫描路径。
 - 通过setBody修改方法体，分别添加me.flyleft.eureka.client.event.EurekaEventHandler.getInstance().eurekaAddInstance($1);和me.flyleft.eureka.client.event.EurekaEventHandler.getInstance().eurekaRemoveInstance($1);
 - 通过toClass覆盖原有类后，通过类加载器重新加载。
 
```java
public void init() {
        try {
            ClassPool classPool = new ClassPool(true);
            //添加com.netflix.discovery包的扫描路径
            ClassClassPath classPath = new ClassClassPath(Applications.class);
            classPool.insertClassPath(classPath);
            //获取要修改Application类
            CtClass ctClass = classPool.get(APPLICATION_PATH);
            //获取addInstance方法
            CtMethod addInstanceMethod = ctClass.getDeclaredMethod("addInstance");
            //修改addInstance方法
            addInstanceMethod.setBody("{instancesMap.put($1.getId(), $1);"
                    + "synchronized (instances) {me.flyleft.eureka.client.event.EurekaEventHandler.getInstance().eurekaAddInstance($1);" +
                    "instances.remove($1);instances.add($1);isDirty = true;}}");
            //获取removeInstance方法
            CtMethod removeInstanceMethod = ctClass.getDeclaredMethod("removeInstance");
            //修改removeInstance方法
            removeInstanceMethod.setBody("{me.flyleft.eureka.client.event.EurekaEventHandler.getInstance().eurekaRemoveInstance($1);this.removeInstance($1, true);}");
            //覆盖原有的Application类
            ctClass.toClass();
            //使用类加载器重新加载Application类
            classPool.getClassLoader().loadClass(APPLICATION_PATH);
            Class.forName(APPLICATION_PATH);
        } catch (Exception e) {
            throw new EurekaEventException(e);
        }
    }
```

- 放入main函数，在spring boot启动前执行或者使用spring boot的事件，在spring bean初始化之前执行。(确保在eureka第一次执行之前执行即可)

```java
@SpringBootApplication
@EnableEurekaClient
public class EurekaClientApplication {

    public static void main(String[] args) {
        //先执行修改字节码代码
        EurekaEventHandler.getInstance().init();
        new SpringApplicationBuilder(EurekaClientApplication.class).web(true).run(args);
    }
}
```


## 使用JDK中Observable和Observer实现观察者，订阅者模式

- 发送事件使用java.util.Observable的setChanged和notifyObservers

```java
public class EurekaEventObservable extends Observable {
    public void sendEvent(EurekaEventPayload payload) {
        setChanged();
        notifyObservers(payload);
    }
}
```

- 接收事件使用使用java.util.Observer的update

```java
public abstract class AbstractEurekaEventObserver implements Observer, EurekaEventService {
      @Override
        public void update(Observable o, Object arg) {
            if (arg instanceof EurekaEventPayload) {
                EurekaEventPayload payload = (EurekaEventPayload) arg;
                if (InstanceInfo.InstanceStatus.UP.name().equals(payload.getStatus())) {
                    LOGGER.info("Receive UP event, payload: {}", payload);
                } else {
                    LOGGER.info("Receive DOWN event, payload: {}", payload);
                }
                putPayloadInCache(payload);
                consumerEventWithAutoRetry(payload);
            }
        }
}
````

## 使用RxJava实现自动重试。

接收到服务启动去执行一些操作，如果执行失败有异常则自动重试指定次数，每个一段事件重试一次，执行成功则不再执行

```java
private void consumerEventWithAutoRetry(final EurekaEventPayload payload) {
    rx.Observable.just(payload)
            .map(t -> {
                // 此处为接收到服务启动去执行的一些操作
                consumerEvent(payload);
                return payload;
            }).retryWhen(x -> x.zipWith(rx.Observable.range(1, retryTime),
            (t, retryCount) -> {
               //异常处理
                if (retryCount >= retryTime) {
                    if (t instanceof RemoteAccessException || t instanceof RestClientException) {
                        LOGGER.warn("error.eurekaEventObserver.fetchError, payload {}", payload, t);
                    } else {
                        LOGGER.warn("error.eurekaEventObserver.consumerError, payload {}", payload, t);
                    }
                }
                return retryCount;
            }).flatMap(y -> rx.Observable.timer(retryInterval, TimeUnit.SECONDS)))
            .subscribeOn(Schedulers.io())
            .subscribe((EurekaEventPayload payload1) -> {
            });
}
```

## 添加手动重试失败接口

自动重试失败，可以手动重试，添加手动重试接口

```java
@RestController
@RequestMapping(value = "/v1/eureka/events")
public class EurekaEventEndpoint {

    private EurekaEventService eurekaEventService;

    public EurekaEventEndpoint(EurekaEventService eurekaEventService) {
        this.eurekaEventService = eurekaEventService;
    }

    @Permission(permissionLogin = true)
    @ApiOperation(value = "获取未消费的事件列表")
    @GetMapping
    public List<EurekaEventPayload> list(@RequestParam(value = "service", required = false) String service) {
        return eurekaEventService.unfinishedEvents(service);
    }

    @Permission(permissionLogin = true)
    @ApiOperation(value = "手动重试未消费成功的事件")
    @PostMapping("retry")
    public List<EurekaEventPayload> retry(@RequestParam(value = "id", required = false) String id,
                                          @RequestParam(value = "service", required = false) String service) {
        return eurekaEventService.retryEvents(id, service);
    }

}
```


## [源码](https://github.com/flyleft/spring-cloud-base/tree/master/eureka-client)