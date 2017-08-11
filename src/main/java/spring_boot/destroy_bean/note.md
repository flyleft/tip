- ## spring 中销毁bean的方式

1. 实现DisposableBean接口
```java
@Component
@Slf4j
public class ByDisposableBean implements DisposableBean {

    @Override
    public void destroy() throws Exception {
        log.info("destroy by DisposableBean");
    }
}
```

2. 通过@PreDestroy注解
```java
@Component
@Slf4j
public class ByPreDestroy {
    @PreDestroy
    public void destroy(){
        log.info("destroy by PreDestroy");
    }
}
```

3. 通过BeanFactory的destroySingletons
> 或者destroyScopedBean(String beanName)和destroyBean(String beanName, Object beanInstance)
```java
@SpringBootApplication
public class ByShutdownHook {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(ByShutdownHook.class)
                .run(args);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> applicationContext.getBeanFactory().destroySingletons()));
    }
}
```
