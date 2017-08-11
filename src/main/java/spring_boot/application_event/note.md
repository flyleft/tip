- ### 参考
  - http://blog.csdn.net/liaokailin/article/details/48186331
  
- ### 事件监听机制
> spring boot在启动过程中增加事件监听机制,支持的事件类型四种

1. ApplicationStartingEvent
    >  spring boot 启动时执行的事件

2. ApplicationEnvironmentPreparedEvent
   > spring boot 对应Enviroment已经准备完毕，但此时上下文context还没有创建。在该监听中获取到ConfigurableEnvironment后可以对配置信息做操作，例如：修改默认的配置信息，增加额外的配置信息等等~~~

3. ApplicationPreparedEvent
  > ApplicationPreparedEvent:spring boot上下文context创建完成，但此时spring中的bean是没有完全加载完成的。在获取完上下文后，可以将上下文传递出去做一些额外的操作。在该监听器中是无法获取自定义bean并进行操作的。

4. ApplicationFailedEvent
  > spring boot启动异常时执行事件 
