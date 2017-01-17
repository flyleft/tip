##[平常收藏的开源项目和技术](https://github.com/jcalaz/tip)

> 会不定时更新

### JAVAEE

- ####权限引擎
  1. [Apache Shiro:是一个强大且易用的Java安全框架,执行身份验证、授权、密码学和会话管理](http://shiro.apache.org/get-started.html)
  2. [Spring Security:是一个能够为基于Spring的企业应用系统提供声明式的安全访问控制解决方案的安全框架](http://projects.spring.io/spring-security/)
  3. [kisso = cookie sso 基于 Cookie 的 SSO 中间件，它是一把快速开发 java Web 登录系统（SSO）的瑞士军刀](http://git.oschina.net/baomidou/kisso)
  
- ####验证码
  1. [JCaptcha:是一个用来生成验证码的开源Java类库，目前最新的版本是2.0。](http://jcaptcha.sourceforge.net/)
  2. [patchca: 简单强大零依赖](https://github.com/pusuo/patchca)
  2. [kaptcha:是一个扩展自 simplecaptcha 的验证码库](https://github.com/axet/kaptcha)
  
- ####分布式框架
  1. [Spring Cloud: 分布式一站式解决，将各家公司开发的比较成熟、经得起实际考验的服务框架组合起来](http://projects.spring.io/spring-cloud/)
  2. [dubbo: 是一个分布式服务框架，致力于提供高性能和透明化的RPC远程服务调用方案，是阿里巴巴SOA服务化治理方案的核心框架](http://dubbo.io/)
  3. [dubbox:当当根据自身的需求，为Dubbo实现了一些新的功能，并将其命名为Dubbox](https://github.com/dangdangdotcom/dubbox)
  4. [finatra: twitter基于scala开发](https://github.com/twitter/finatra)
  5. [motan: 新浪轻量级RPC框架](https://github.com/weibocom/motan)
  6. [Thrift: facebook开源，支持多种语言](http://thrift.apache.org/)
  7. [grpc: 谷歌开源，支持跨语言](http://www.grpc.io/)
  
- ####模板引擎
   1. [freemarker: 老牌模板引擎，没用过](http://freemarker.org/docs/)
   2. [velocity: 老牌模板引擎，用过不错，就是不再更新了](http://velocity.apache.org/)
   3. [thymeleaf: 3.0出了，性能稍微差点，不过越来越快了，不跑服务器可以直接在浏览器显示，开发起来比较爽](http://www.thymeleaf.org/documentation.html)
   4. [beetl: 国产，性能高](http://ibeetl.com/)
   
- ####依赖注入
   1. [Spring IOC: 不用说了，注解后越来越爽了](http://projects.spring.io/spring-framework/)
   2. [google guice: 谷歌出品，更轻量级，速度更快，但是开发效率感觉不如spring ioc高，尤其现在到处spring全家桶](https://github.com/google/guice)
   
- ####ORM
   1. [Mybatis: 支持定制化 SQL、存储过程以及高级映射的优秀的持久层框架，需要手写sql，灵活，可控性强](http://www.mybatis.org/mybatis-3/zh/)
   2. [Spring data JPA: Hibernate二次封装，开发效率高，自动帮你完成很多东西](http://projects.spring.io/spring-data-jpa/)
   3. [Hibernate: 老牌强大ORM，用的不多，开发效率相比spring data jpa感觉差不少](http://hibernate.org/orm/)
   4. [JOOQ: DSL写法，有点类似动态语言了，感觉中小项目用起来会很爽](http://www.jooq.org/)
   5. [spring data mongo: 类似spring data jpa，开发效率高，复杂操作使用MongoTemplate](http://projects.spring.io/spring-data-mongodb/)
   
- ####日志
   1. [commons-logging: 日志接口，会自动装载具体的日志系统，采用ClassLoader寻找和载入底层的日志库，没有第三方会使用JDK自带](http://commons.apache.org/proper/commons-logging/)
   2. [slf4j: 日志接口，通过各种桥接包判断实现，在编译时静态绑定真正的日志库](http://www.slf4j.org/)
   3. [log4j: 日志实现，较早](http://logging.apache.org/log4j/1.2/)
   4. [logback: 日志实现，相比log4j，性能更好，功能更强大](http://logback.qos.ch/)
   5. [log4j2: 日志实现，配置更强大，支持插件化，使用Disruptor实现异步日志，性能最好](http://logging.apache.org/log4j/2.x/)
   
- ####全文搜索
   1. [ElasticSearch: 基于Lucene RESTful web接口的搜索服务器。](https://www.elastic.co/guide/en/elasticsearch/reference/current/search-search.html)
   2. [Solr: 高性能，基于Lucene的全文搜索服务器](http://lucene.apache.org/solr/)
   3. [Lucene: 一个全文检索引擎的架构，提供了完整的查询引擎和索引引擎，部分文本分析引擎](https://lucene.apache.org/)
   
- ####数据校验
    1. [Hibernate Validator](http://hibernate.org/validator/)
    2. [Bean Validation: 基于Hibernate Validator](http://beanvalidation.org/)
    
- ####restful
    1. [spring mvc: 类加个@RestController或者方法加个@ResponseBody](https://spring.io/guides/gs/rest-service/)
    2. [jersey: 实现了JAX-RS规范](https://jersey.java.net/)
    3. [resteasy: JBoss项目，实现了JAX-RS规范，性能高 ](http://resteasy.jboss.org/)
    
- ####消息中间件
    1. [ActiveMQ: 完全支持JMS1.1和J2EE 1.4规范的消息队列](http://activemq.apache.org/)
    2. [RabbitMq:Erlang编写，在AMQP基础上完整的，可复用的企业消息系统](http://www.rabbitmq.com/)
    3. [kafka: scala编写的高吞吐量的分布式发布订阅消息系统，常用于日志](http://kafka.apache.org/)
    4. [RocketMQ: 阿里巴巴的MQ中间件,在其多个产品下使用，并能够撑住双十一的大流量](https://github.com/apache/incubator-rocketmq)

- #### 其他
    1. [spring-retry: 操作失败后，优雅自动重新尝试](https://github.com/spring-projects/spring-retry)
     
  
### JVM和字节码

- #### 字节码工具
  1. [ASM:  是一个 Java 字节码操控框架。它能够以二进制形式修改已有类或者动态生成类,性能高，但不如javassist简单](http://asm.ow2.org/)
  2. [Javassist: 是一个开源的分析、编辑和创建Java字节码的类库,无须了解JVM指令，java编码即可](http://jboss-javassist.github.io/javassist/)
  3. [jd-gui: java反编译](http://jd.benow.ca/)
  4. [jbe : java字节码修改工具](http://www.cs.ioc.ee/~ando/jbe/)
  
- ####JVM编程语言
  1. [groovy: 类似ruby的动态语言，gradle的编写语言](http://www.groovy-lang.org/)
  2. [scala: 强大的多范式编程语言，有spark，akka，kafka等开源项目](http://www.scala-lang.org/)
  3. [clojure: 动态Lisp方言](https://www.clojure.org/)
  4. [kotlin: jetbrains开发，目前多用于安卓开发，简化版的scala](http://kotlinlang.org/)
  5. [lux: 正在开发中的静态lisp方言](https://github.com/LuxLang/lux)