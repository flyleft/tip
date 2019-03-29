# 自写博客或笔记
- [并发优化 - 降低锁颗粒](https://github.com/flyleft/tip/blob/master/blog/并发优化%20-%20降低锁颗粒.md)
- [数据一致性(一) - 接口调用一致性](https://github.com/flyleft/tip/blob/master/blog/数据一致性(一)%20-%20接口调用一致性.md)
- [go、java对比](https://github.com/flyleft/tip/blob/master/blog/go、java对比.md)
- [go使用struct tag实现类似于spring boot的yaml配置文件，源码：](https://github.com/flyleft/gprofile)
- [使用javassist修改字节码实现eureka-client监听服务启动](https://github.com/flyleft/tip/blob/master/blog/使用javassist修改字节码实现eureka-client监听服务启动.md)
- [用JAVA二十分钟撸一个简易图片防篡改](blog/javase/用JAVA二十分钟撸一个简易图片防篡改.md)
- [自制编程语言之用scala写一个简单分词器](blog/自制编程语言之用scala写一个简单分词器.md)
- [git flow的使用](blog/git_flow使用手册.md)
- [java中使用pact做契约测试](blog/java中使用pact做契约测试.md)
- [zuul性能调优-使用okhttp替代httpclient](blog/zuul性能调优-okhttp替代httpclient.md)
- [使用spock编写spring-boot项目的单元测试](blog/使用spock编写spring-boot项目的单元测试.md)
- [java中JNI的使用](https://github.com/flyleft/tip/blob/master/blog/java中JNI的使用.md)

---

# 博客收藏
- [Linux下的I/O复用与epoll详解](https://www.cnblogs.com/lojunren/p/3856290.html)
- [数据库事务隔离级别](https://juejin.im/post/5b90cbf4e51d450e84776d27?utm_source=tuicool&utm_medium=referral)
- [微服务注册中心 Eureka 架构深入解读](https://www.infoq.cn/article/jlDJQ*3wtN2PcqTDyokh?utm_source=tuicool&utm_medium=referral)
- [分布式Bitmap实践：Naix](https://juejin.im/post/5ba30de85188255c5442a34a)
- [InnoDB并发如此高，原因竟然在这？](https://mp.weixin.qq.com/s?__biz=MjM5ODYxMDA5OQ==&mid=2651961444&idx=1&sn=830a93eb74ca484cbcedb06e485f611e&chksm=bd2d0db88a5a84ae5865cd05f8c7899153d16ec7e7976f06033f4fbfbecc2fdee6e8b89bb17b&scene=21#wechat_redirect)
- [Redis高可用技术解决方案总结](http://dbaplus.cn/news-158-2182-1.html?utm_source=tuicool&utm_medium=referral)
- [kafka的exactly once支持](https://www.confluent.io/blog/exactly-once-semantics-are-possible-heres-how-apache-kafka-does-it/)
- [janus 性能优化与 JVM 调优实践](https://mp.weixin.qq.com/s/dbgLn55Ki9pfdEerbu7awg?utm_source=tuicool&utm_medium=referral)
- [数据库两大神器【索引和锁】](https://juejin.im/post/5b55b842f265da0f9e589e79)
- [HTTP缓存机制](https://juejin.im/post/5a1d4e546fb9a0450f21af23)
- [完全吃透 TLS/SSL](https://juejin.im/post/5b305758e51d4558ce5ea0d9?utm_source=tuicool&utm_medium=referral)
- [分布式理论(一) - CAP定理](https://juejin.im/post/5b26634b6fb9a00e765e75d1?utm_source=tuicool&utm_medium=referral)
- [分布式理论(二) - BASE理论](https://juejin.im/post/5b2663fcf265da59a401e6f8)
- [分布式理论(三) - 2PC协议](https://juejin.im/post/5b2664446fb9a00e4a53136e)
- [分布式理论(四) - 3PC协议](https://juejin.im/post/5b26648e5188257494641b9f)
- [分布式理论(五) - 一致性算法Paxos](https://juejin.im/post/5b2664bd51882574874d8a76)
- [分布式理论(六) - 一致性协议Raft](https://juejin.im/post/5b2664e2f265da59584d8c90)
- [聊聊分布式存储——图解Paxos](https://www.jianshu.com/p/002a357d5993?utm_source=tuicool&utm_medium=referral)
- [Raft动画讲解](http://thesecretlivesofdata.com/raft/)
- [GTS:微服务架构下分布式事务解决方案](https://zhuanlan.zhihu.com/p/37492435?utm_source=tuicool&utm_medium=referral)
- [Gossip协议在Cassandra中的实现](https://juejin.im/entry/59900b585188257d86431847)
- [一篇的比较全面的 RxJava2 方法总结](https://juejin.im/post/5b72f76551882561354462dd)
- [RxJava2：背压和Flowable](https://juejin.im/post/5b759b9cf265da283719d187)
- [RxJava2：使用 Subject](https://juejin.im/post/5b801dfa51882542cb409905)


---

# 开源
- #### 并发
  - *线程安全set*：`ConcurrentSkipListSet`、`CopyOnWriteArraySet`、`ConcurrentHashMap.newKeySet()`、`Collections.synchronizedSet(set)`
   
- 分布式技术
  - *缓存穿透*: 请求去查询缓存数据库中根本就不存在的数据。解决方案: `缓存空值`、`BloomFilter`
  - *缓存雪崩*: 缓存层不能提供服务，所有的请求都会达到存储层，存储层的调用量会暴增，造成存储层挂掉。解决方案: `缓存集群`、`本地缓存`、`限流`、`降级`
  
   
- #### 算法和协议
   1.  [Redlock算法: 使用Redis实现分布式锁的算法](https://github.com/redisson/redisson/wiki/8.-Distributed-locks-and-synchronizers)
   2. [Snowflake: Twitter的分布式自增ID算法](https://github.com/twitter-archive/snowflake/releases/tag/snowflake-2010)
 
- #### 数据一致性
   1. [fescar: 阿里开源分布式事务解决方案](https://github.com/alibaba/fescar)
       
- #### 容器与devops
   1. [Knative：用于构建、部署和管理Serverless工作负载的Kubernetes框架](https://github.com/knative)
   2. [TAC：基于java的微服务容器，提供从业务代码编写、编译、发布、jar动态加载、运行等一系列常用开发流程的支持](https://github.com/alibaba/tac)
   3. [Supervisor：进程管理工具，是通过fork/exec的方式将这些被管理的进程当作supervisor的子进程来启动。](http://www.supervisord.org)
   4. [gVisor：Google开源的轻量级的Sandbox，额外的内存消耗非常小，但同时提供了和VM方案相当隔离等级。](https://github.com/google/gvisor)
   5. [Pouch：阿里巴巴开源的自研容器技术](https://github.com/alibaba/pouchs)
   6. [container-diff: Google 开源 Docker 镜像差异分析工具](https://github.com/GoogleCloudPlatform/container-diff)
   7. [Kubernetes： 谷歌开源用于自动化部署，扩展和管理集装箱化应用程序的系统](https://kubernetes.io/)

- #### goLang
  1. [awesome-go: go的开源项目收集](https://github.com/avelino/awesome-go)
  2. [cobra: CLI命令行库，也可用于生成程序应用和命令行文件](https://github.com/spf13/cobra)
  3. [machinery: go的分布式任务队列](https://github.com/RichardKnop/machinery)
  4. [iris: 支持http2,websocket,MVC的高性能框架](https://github.com/kataras/iris)
  5. [go-kit: go微服务开发工具集](https://github.com/go-kit/kit)
  6. [protoactor-go:go的Actor模型并发库](https://github.com/AsynkronIT/protoactor-go)
  7. [uber-go/dig: go依赖注入](https://github.com/uber-go/dig.git)
  8. [pkg/errors: go异常处理库](https://github.com/pkg/errors.git)
  9. [Dragonboat: 高性能raft库](https://github.com/lni/dragonboat)
  10. [revive: go的代码质量检测工具](https://github.com/mgechev/revive)
  11. [gotests: 快速生成单元测试的命令行工具](https://github.com/cweill/gotests)
  
- #### 性能调优工具
   1. [TProfiler: 是一个阿里开源可以在生产环境长期使用的JVM性能分析工具](https://github.com/alibaba/TProfiler)
   2. [pprof: 一个开源goLang的性能分析工具](https://github.com/google/pprof)
   3. [arthas: 阿里开源java诊断工具](https://github.com/alibaba/arthas.git)

- #### 服务发现
   1. [ZooKeeper: 是一个分布式的，开放源码的分布式应用程序协调服务，使用Paxos算法](http://zookeeper.apache.org/)
   2. [Etcd: 是一个键值存储仓库，用于配置共享和服务发现, 使用Raft算法。](https://github.com/coreos/etcd) 
   3. [Consul: HashiCorp公司推出的开源工具，用于实现分布式系统的服务发现与配置, 使用Raft算法。](https://github.com/hashicorp/consul)
   4. [Eureka: Netflix开源的，java开发，基于restful。](https://github.com/Netflix/eureka)

- #### service mesh
   1. [Istio: Google/IBM/Lyft共同开发的新一代Service Mesh开源项目, 运行在k8s上](https://istio.io/)
   1. [sofa-mesh: 蚂蚁金服基于 Istio 改进和扩展而来的 Service Mesh 大规模落地实践方案](https://github.com/alipay/sofa-mesh)
   1. [sofa-mosn: 蚂蚁金服基于 Golang 开发的Service Mesh数据平面代理, MOSN支持Envoy和Istio的API，可以和Istio集成](https://github.com/alipay/sofa-mosn)
   
- #### 分布式框架
  1. [Spring Cloud: 分布式一站式解决，将各家公司开发的比较成熟、经得起实际考验的服务框架组合起来](http://projects.spring.io/spring-cloud/)
  2. [dubbo: 是一个分布式服务框架，致力于提供高性能和透明化的RPC远程服务调用方案，是阿里巴巴SOA服务化治理方案的核心框架](http://dubbo.io/)
  3. [dubbox:当当根据自身的需求，为Dubbo实现了一些新的功能，并将其命名为Dubbox](https://github.com/dangdangdotcom/dubbox)
  4. [finatra: twitter基于scala开发](https://github.com/twitter/finatra)
  5. [motan: 新浪轻量级RPC框架](https://github.com/weibocom/motan)
  6. [Thrift: facebook开源，支持多种语言](http://thrift.apache.org/)
  7. [grpc: 谷歌开源，支持跨语言](http://www.grpc.io/)
  8. [Istio: Google/IBM/Lyft共同开发的新一代Service Mesh开源项目](https://istio.io/)
  9. [SOFARPC: 蚂蚁金服一个高可扩展性、高性能、生产级的 Java RPC 框架, 提供了丰富的模型抽象和可扩展接口。](https://github.com/alipay/sofa-rpc.git)
  
- ##### 权限引擎
  1. [Apache Shiro:是一个强大且易用的Java安全框架,执行身份验证、授权、密码学和会话管理](http://shiro.apache.org/get-started.html)
  2. [Spring Security:是一个能够为基于Spring的企业应用系统提供声明式的安全访问控制解决方案的安全框架](http://projects.spring.io/spring-security/)
  3. [casbin: 一个goLang开源访问控制框架，支持ACL, RBAC, ABAC等](https://github.com/casbin/casbin)
  
- #### 模板引擎
   1. [freemarker: 老牌模板引擎，没用过](http://freemarker.org/docs/)
   2. [velocity: 老牌模板引擎，不错，就是不再更新了](http://velocity.apache.org/)
   3. [thymeleaf: 3.0出了，性能稍微差点，不过越来越快了，不跑服务器可以直接在浏览器显示，开发起来比较爽](http://www.thymeleaf.org/documentation.html)
   
- #### 依赖注入
   1. [Spring IOC: 不用说了，注解后越来越爽了](http://projects.spring.io/spring-framework/)
   2. [google guice: 谷歌出品，更轻量级，速度更快，但是开发效率感觉不如spring ioc高，尤其现在到处spring全家桶](https://github.com/google/guice)
   
- #### ORM
   1. [Mybatis: 支持定制化 SQL、存储过程以及高级映射的优秀的持久层框架，需要手写sql，灵活，可控性强](http://www.mybatis.org/mybatis-3/zh/)
   2. [Spring data JPA: Hibernate二次封装，开发效率高，自动帮你完成很多东西](http://projects.spring.io/spring-data-jpa/)
   3. [Hibernate: 老牌强大ORM，用的不多，开发效率相比spring data jpa感觉差不少](http://hibernate.org/orm/)
   4. [JOOQ: DSL写法，有点类似动态语言了，感觉中小项目用起来会很爽](http://www.jooq.org/)
   5. [spring data mongo: 类似spring data jpa，开发效率高，复杂操作使用MongoTemplate](http://projects.spring.io/spring-data-mongodb/)
   
- #### 日志
   1. [commons-logging: 日志接口，会自动装载具体的日志系统，采用ClassLoader寻找和载入底层的日志库，没有第三方会使用JDK自带](http://commons.apache.org/proper/commons-logging/)
   2. [slf4j: 日志接口，通过各种桥接包判断实现，在编译时静态绑定真正的日志库](http://www.slf4j.org/)
   3. [log4j: 日志实现，较早](http://logging.apache.org/log4j/1.2/)
   4. [logback: 日志实现，相比log4j，性能更好，功能更强大](http://logback.qos.ch/)
   5. [log4j2: 日志实现，配置更强大，支持插件化，使用Disruptor实现异步日志，性能最好](http://logging.apache.org/log4j/2.x/)
   
- #### 搜索引擎
   1. [ElasticSearch: 基于Lucene RESTful web接口的搜索服务器。](https://www.elastic.co/guide/en/elasticsearch/reference/current/search-search.html)
   2. [Solr: 高性能，基于Lucene的全文搜索服务器](http://lucene.apache.org/solr/)
   3. [Lucene: 一个全文检索引擎的架构，提供了完整的查询引擎和索引引擎，部分文本分析引擎](https://lucene.apache.org/)
   4. [Nutch: 提供了我们运行自己的搜索引擎所需的全部工具,包括全文搜索和Web爬虫](http://nutch.apache.org/)
   
- #### 爬虫
    1. [Heritrix: 最出色之处在于它良好的可扩展性，方便用户实现自己的抓取逻辑](https://webarchive.jira.com/wiki/display/Heritrix)
    2. [jsoup: 一款Java 的HTML解析器，可直接解析URL地址、HTML文本内容](https://jsoup.org/)
    3. [crawler4j: 提供了简单易用的接口，可以在几分钟内创建一个多线程网络爬虫](https://github.com/yasserg/crawler4j)
    4. [WebCollector: 一个无须配置、便于二次开发爬虫内核，它提供精简的的API，只需少量代码即可实现一个功能强大的爬虫](http://crawlscript.github.io/WebCollector/)
    5. [WebMagic: 国产，完全模块化的设计,支持多线程抓取，分布式抓取，并支持自动重试、自定义UA/cookie等功能](https://git.oschina.net/flashsword20/webmagic)
    
   
- #### 数据校验
    1. [Hibernate Validator](http://hibernate.org/validator/)
    2. [Bean Validation: 基于Hibernate Validator](http://beanvalidation.org/)
    
- #### restful
    1. [spring mvc: 类加个@RestController或者方法加个@ResponseBody](https://spring.io/guides/gs/rest-service/)
    2. [jersey: 实现了JAX-RS规范](https://jersey.java.net/)
    3. [resteasy: JBoss项目，实现了JAX-RS规范，性能高 ](http://resteasy.jboss.org/)
    
- #### 消息中间件
    1. [ActiveMQ: 完全支持JMS1.1和J2EE 1.4规范的消息队列](http://activemq.apache.org/)
    2. [RabbitMq:Erlang编写，在AMQP基础上完整的，可复用的企业消息系统](http://www.rabbitmq.com/)
    3. [kafka: scala编写的高吞吐量的分布式发布订阅消息系统，常用于日志](http://kafka.apache.org/)
    4. [RocketMQ: 阿里巴巴的MQ中间件,在其多个产品下使用，并能够撑住双十一的大流量](https://github.com/apache/incubator-rocketmq)
    5. [Apache Pulsar: 雅虎开发的企业级的发布订阅消息系统](http://pulsar.incubator.apache.org/)
    6. [Apache DistributedLog: Twitter开发的一个低延时（毫秒级）、高吞吐的分布式复制日志流系统](https://bookkeeper.apache.org/distributedlog/)

- #### 响应式编程
    1. [reactor: spring社区开源，在spring 5.0直接引入](http://projectreactor.io/)
    2. [vert.x: 基于netty,可以通过它使用JavaScript、Ruby、Groovy、Java、甚至是混合语言来编写应用](http://vertx.io/)
    3. [RxJava: 安卓上用的特别多，特别与RxAndroid,Retrofit等结合使用](https://github.com/ReactiveX/RxJava)
    
- #### 热加载
    1. [JRebel: 收费，idea，eclipse，netbeans都有它的插件](https://zeroturnaround.com/software/jrebel/)
    2. [spring-loaded: spring开源热更新工具](https://github.com/spring-projects/spring-loaded)
    3. [spring-boot-devtools: spring boot子模块，热更新工具](https://github.com/spring-projects/spring-boot/tree/master/spring-boot-devtools)
    3. [HotswapAgent : 免费开源热更新](https://github.com/HotswapProjects/HotswapAgent)
    
- #### 数据库连接池
    1. [HikariCP: 性能最高的数据库连接池](http://brettwooldridge.github.io/HikariCP/)
    2. [druid: 阿里出品，为监控而生的数据库连接池](https://github.com/alibaba/druid)

- #### 测试工具
    1. [Mockito: 允许使用自动化的单元测试创建和测试双对象，以达到测试驱动开发和行为驱动开发的目的](http://site.mockito.org/)
    2. [JUnit: 最常使用的单元测试工具](http://junit.org/junit4/)
    3. [TestNG: 主要功能是覆盖范围更广的测试分类，如单元、功能性、端到端，一体化等](http://testng.org/doc/index.html)
    4. [greys-anatomy: Java问题在线诊断工具](https://github.com/oldmanpushcart/greys-anatomy)
    5. [jmh: 性能微基准测试工具](http://openjdk.java.net/projects/code-tools/jmh/)
    6. [jmeter：Apache开发的基于Java的压力测试工具](https://jmeter.apache.org)
    7. [Gatling: scala开发的开源功能强大的负载测试工具](https://github.com/gatling/gatling)
    8. [ab: apache自带的简单压测工具](https://httpd.apache.org/docs/2.4/programs/ab.html)
    9. [wrk: 开源HTTP压测工具](https://github.com/wg/wrk)
  
- #### 序列化
    1. [FlatBuffers: 相较于Protocol Buffers，其更适用于移动设备](https://github.com/google/flatbuffers)
    2. [protobuf: 高性能，跨语言，也是google出品](https://github.com/google/protobuf)
    3. [jprotobuf: 针对Java程序开发一套简易类库，目的是简化java语言对protobuf类库的使用](https://github.com/jhunters/jprotobuf)
    3. [FST: 重新实现的Java快速对象序列化的开发包,序列化速度更快、体积更小，而且兼容JDK原生的序列化](https://github.com/RuedigerMoeller/fast-serialization)
    4. [Kryo: 快速高效的Java序列化框架,支持论文件、数据库或网络数据，自动深拷贝、浅拷贝](https://github.com/EsotericSoftware/kryo)
    5. [MessagePack: 基于二进制高效的对象序列化类库,跨语言,比JSON更快速也更轻巧](https://github.com/msgpack/msgpack)
    6. [thrift: 跨语言，不仅仅包括序列化，是一个远程服务调用框架，facebook开源](http://thrift.apache.org/)
    
- #### 数据库工具
    1. [Flyway : 是一个敏捷工具，用于数据库的移植](https://flywaydb.org/)
    2. [liquibase: 一个用于跟踪、管理和应用数据库变化的开源的数据库重构工具。支持xml，groovy编写](http://www.liquibase.org/)
    3. [Apache  Impala: 是一个高性能分析数据库，可针对存储在 Apache Hadoop 集群中的 PB 级数据进行闪电般快速的分布式 SQL 查询](http://impala.apache.org/community.html)
    4. [MyFlash: 美团点评的开源MySQL闪回工具](https://github.com/Meituan-Dianping/MyFlash)
    5. [Ctrip DAL: 是携程框架部开发的数据库访问框架，支持代码生成和水平扩展。](https://github.com/ctripcorp/dal)
    
- #### 服务发现
    1. [ZooKeeper: 是一个分布式的，开放源码的分布式应用程序协调服务，使用Paxos算法。](http://zookeeper.apache.org/)
    2. [Etcd: 是一个键值存储仓库，用于配置共享和服务发现, 使用Raft算法。](https://github.com/coreos/etcd) 
    3. [Consul: HashiCorp公司推出的开源工具，用于实现分布式系统的服务发现与配置, 使用Raft算法。](https://github.com/hashicorp/consul)
    4. [Eureka: Netflix开源的，java开发，基于restful。](https://github.com/Netflix/eureka)

- #### 配置相关
    1. [apollo: 携程框架部门研发的配置管理平台](https://github.com/ctripcorp/apollo)
     
- #### 契约测试
    1. [Pact: 提供对消费者驱动的契约测试的支持，支持多种语言](https://www.pact.net.cn)
    2. [Spring Cloud Contract: 一个消费者驱动的微服务契约测试套件](https://github.com/spring-cloud/spring-cloud-contract)

    
- #### 字节码工具
  1. [ASM:  是一个 Java 字节码操控框架。它能够以二进制形式修改已有类或者动态生成类,性能高，但不如javassist简单](http://asm.ow2.org/)
  2. [Javassist: 是一个开源的分析、编辑和创建Java字节码的类库,无须了解JVM指令，java编码即可](http://jboss-javassist.github.io/javassist/)
  3. [jd-gui: java反编译](http://jd.benow.ca/)
  4. [jbe : java字节码修改工具](http://www.cs.ioc.ee/~ando/jbe/)
  
- #### JVM编程语言
  1. [groovy: 类似ruby的动态语言，gradle的编写语言](http://www.groovy-lang.org/)
  2. [scala: 强大的多范式编程语言，有spark，akka，kafka等开源项目](http://www.scala-lang.org/)
  3. [clojure: 动态Lisp方言](https://www.clojure.org/)
  4. [kotlin: jetbrains开发，目前多用于安卓开发，简化版的scala](http://kotlinlang.org/)
  5. [lux: 正在开发中的静态lisp方言](https://github.com/LuxLang/lux)
