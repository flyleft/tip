### 示例项目
   - [asgard-service](https://code.choerodon.com.cn/choerodon-framework/choerodon-asgard-service.git)
   
### 技术
   - 框架: [spock](http://blog.codepipes.com/testing/spock-vs-junit.html)
   - 语言: [groovy](http://www.groovy-lang.org)
 
### pom依赖和插件

```xml
 <!-- Test Dependencies -->
 <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.choerodon</groupId>
            <artifactId>choerodon-liquibase</artifactId>
            <version>${choerodon.starters.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>1.4.197</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.spockframework</groupId>
            <artifactId>spock-core</artifactId>
            <version>1.1-groovy-2.4-rc-2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.spockframework</groupId>
            <artifactId>spock-spring</artifactId>
            <version>1.1-groovy-2.4-rc-3</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/cglib/cglib-nodep -->
        <dependency>
            <groupId>cglib</groupId>
            <artifactId>cglib-nodep</artifactId>
            <version>2.2</version>
            <scope>test</scope>
        </dependency>
   </dependencies>
   
   <build>
           <finalName>app</finalName>
           <plugins>
               <plugin>
                   <groupId>org.codehaus.gmavenplus</groupId>
                   <artifactId>gmavenplus-plugin</artifactId>
                   <version>1.5</version>
                   <executions>
                       <execution>
                           <goals>
                               <goal>addTestSources</goal>
                               <goal>testCompile</goal>
                           </goals>
                       </execution>
                   </executions>
               </plugin>
               <plugin>
                   <groupId>org.apache.maven.plugins</groupId>
                   <artifactId>maven-compiler-plugin</artifactId>
                   <version>3.3</version>
                   <configuration>
                       <source>1.8</source>
                       <target>1.8</target>
                   </configuration>
               </plugin>
               <plugin>
                   <groupId>org.apache.maven.plugins</groupId>
                   <artifactId>maven-surefire-plugin</artifactId>
                   <version>2.18.1</version>
                   <configuration>
                       <includes>
                           <include>**/*Test.java</include>
                           <include>**/*Spec.java</include>
                       </includes>
                   </configuration>
               </plugin>
           </plugins>
       </build>
```

### 目录结构
   ![目录](structure.png)
   - IntegrationTestConfiguration.groovy

   ```groovy
   @TestConfiguration
   @Import(LiquibaseConfig)
   class IntegrationTestConfiguration {
   
       private final detachedMockFactory = new DetachedMockFactory()
   
       @Value('${choerodon.oauth.jwt.key:choerodon}')
       String key
   
       @Autowired
       TestRestTemplate testRestTemplate
   
       @Autowired
       LiquibaseExecutor liquibaseExecutor
   
       final ObjectMapper objectMapper = new ObjectMapper()
   
       //Mock KafkaTemplate, 以防spring-for-kafka影响报错
       @Bean
       KafkaTemplate kafkaTemplate() {
           detachedMockFactory.Mock(KafkaTemplate)
       }
   
       @PostConstruct
       void init() {
           //通过liquibase初始化h2数据库
           liquibaseExecutor.execute()
           //给TestRestTemplate的请求头部添加JWT
           setTestRestTemplateJWT()
       }
   
       private void setTestRestTemplateJWT() {
           testRestTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory())
           testRestTemplate.getRestTemplate().setInterceptors([new ClientHttpRequestInterceptor() {
               @Override
               ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
                   httpRequest.getHeaders()
                           .add('JWT_Token', createJWT(key, objectMapper))
                   return clientHttpRequestExecution.execute(httpRequest, bytes)
               }
           }])
       }
   
       static String createJWT(final String key, final ObjectMapper objectMapper) {
           Signer signer = new MacSigner(key)
           CustomUserDetails defaultUserDetails = new CustomUserDetails('default', 'unknown', Collections.emptyList())
           defaultUserDetails.setUserId(0L)
           defaultUserDetails.setOrganizationId(0L)
           defaultUserDetails.setLanguage('zh_CN')
           defaultUserDetails.setTimeZone('CCT')
           String jwtToken = null
           try {
               jwtToken = 'Bearer ' + JwtHelper.encode(objectMapper.writeValueAsString(defaultUserDetails), signer).getEncoded()
           } catch (IOException e) {
               e.printStackTrace()
           }
           return jwtToken
       }
   
   }
   ```
   
   - application-test.yml

```yml
spring:
  cloud:
    bus:
      enabled: false # 关闭bus,否则kafka报错
  sleuth:
    stream:
      enabled: false # 关闭zipkin，否则kafka报错
  datasource:  # 使用内存数据库h2
    password: sa
    url: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=Mysql;TRACE_LEVEL_SYSTEM_OUT=2;
    username: sa
  autoconfigure: # 关闭LiquibaseAutoConfiguration和KafkaAutoConfiguration的自动化配置
    exclude: org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration,org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
hystrix:
  stream:
    queue:
      enabled: false # 关闭hystrix stream，否则kafka报错
data:
  dir: src/main/resources
eureka:
  client:
    enabled: false # 关闭eureka
```

### 编写测试groovy脚本

1. 添加注解@SpringBootTest(webEnvironment = RANDOM_PORT)和@Import(IntegrationTestConfiguration)。
2. 继承Specification且类名为Spec后缀。
3. 注入所需要测试的类对象
4. 通过given(前提条件),when(触发条件),then(期望结果)

```groovy
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(IntegrationTestConfiguration)
class SagaTaskMapperSpec extends Specification {

    @Autowired
    SagaTaskMapper sagaTaskMapper
    
    def 'insert'() {
        given: '创建一个bean'
         SagaTask sagaTask = new SagaTask()
        def testCode = 'test_code'
        def testSagaCode = 'test_saga_code'
        sagaTask.setCode(testCode)
        sagaTask.setSagaCode(testSagaCode)
        sagaTask.setSeq(1)
        sagaTask.setIsEnabled(true)
        sagaTask.setMaxRetryCount(1)

        when: '插入数据库'
        sagaTaskMapper.insert(sagaTask)

        then: '返回ID'
        sagaTask.getId() != null

        when: '根据ID在数据库查询'
        def data = sagaTaskMapper.selectByPrimaryKey(sagaTask.getId())

        then: '对比数据'
        data.getCode() == testCode
        data.getSagaCode() == testSagaCode
        data.getSeq() == 1
        data.getIsEnabled()
        data.getMaxRetryCount() == 1
    }
}
```

### 其他

  如果在idea不能直接编译运行测试groovy文件，可能是因idea没有识别test/groovy目录。
  ![test](test-dir.png)

### spock其他用法
   1. @Shared：多个测试方法中共享数据
   2. @Stepwise: 当测试方法间存在依赖关系时，标明测试方法将严格按照其在源代码中声明的顺序执行
   3. setupSpec(): 设置每个测试类的环境
   4. setup(): 设置每个测试方法的环境，每个测试方法执行一次
   5. cleanup(): 清理每个测试方法的环境，每个测试方法执行一次
   6. cleanupSepc(): 清理每个测试类的环境
   7. @Ignore: 忽略测试方法
   8. @IgnoreRest：只测试这个方法，而忽略所有其他方法
   9. @Timeout： 设置测试方法的超时时间，默认单位为秒
   
### 参考
   1. https://blog.csdn.net/u012129558/article/details/78675421
   2. http://blog.codepipes.com/testing/spock-vs-junit.html
