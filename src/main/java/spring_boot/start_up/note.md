## spring boot 启动操作的几种方式

>  需要在容器启动的时候执行一些内容，比如：读取配置文件信息，数据库连接，删除临时文件，
   清除缓存信息，在spring框架下是通过ApplicationListener监听器来实现的。
   在Spring Boot中给我们提供了两个接口来帮助我们实现这样的需求。
   这两个接口就是我们今天要讲的CommandLineRunner和ApplicationRunner，他们的执行时机为容器启动完成的时候。

- CommandLineRunner
- ApplicationRunner
- @PostConstruct

### ApplicationRunner和CommandLineRunner
- 不同点: 接收参数分别为ApplicationArguments和String... args类型
- 可以通过@Order设置运行顺序


