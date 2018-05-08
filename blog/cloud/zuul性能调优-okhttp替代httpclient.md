## zuul性能调优-使用okhttp替代httpclient

写一个简单的接口，sleep 4秒，通过zuul对该接口压力测试。

```java
@GetMapping("/test")
    public String test() {
        try {
            logger.info("=== {}", System.currentTimeMillis());
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "test";
    }
```

设置jemter线程数为70，Ramp-UP Period设置为0，即70并发，对该接口通过zuul进行压测。

![jemter](../../pics/zuul_jmeter02.png)

发现一个神奇的问题，每个50个用时翻倍。

对zuul进行debug，请求在RibbonRoutingFilter中进行转发，转发代码位于RibbonCommandContext commandContext = buildCommandContext(context);
ClientHttpResponse response = forward(commandContext);使用Ribbon和hystrix进行转发请求，默认HTTP客户端为HttpClient，完整debug走了两边，
没有50翻倍问题的相关代码，因此怀疑是httpclient的连接池等的原因，因此先把Ribbon的客户端换成okhttp试了一下再进行压测。
 
```
zuul网关依赖中添加
<dependency>
    <groupId>com.squareup.okhttp3</groupId>
    <artifactId>okhttp</artifactId>
</dependency>
配置文件中添加
ribbon:
  httpclient:
    enabled: true
  okhttp:
    enabled: false
```

发现已经不存在50翻倍问题，HTTP响应时间稳定。debug分析一下httpclient和okhttp

### httpclient
把ribbon的客户端换成httpclient，逐步debug，最后到了PoolingHttpClientConnectionManager，里面有个CPool，明显是socket连接池相关，debug
发现defaultMaxPerRoute为50，参考博客发现果真该原因所致。
1、MaxtTotal是整个池子的大小；
2、DefaultMaxPerRoute是根据连接到的主机对MaxTotal的一个细分；比如：
MaxtTotal=400 DefaultMaxPerRoute=200
而我只连接到http://sishuok.com时，到这个主机的并发最多只有200；而不是400；
而我连接到http://sishuok.com 和 http://qq.com时，到每个主机的并发最多只有200；即加起来是400（但不能超过400）；所以起作用的设置是DefaultMaxPerRoute。
即是每个路由连接大小的控制。

查看代码引用，发现RibbonLoadBalancingHttpClient把Ribbon的MaxConnectionsPerHost赋值给了httpclient的DefaultMaxPerRoute

```java
protected HttpClient createDelegate(IClientConfig config) {
  return HttpClientBuilder.create()
    // already defaults to 0 in builder, so resetting to 0 won't hurt
    .setMaxConnTotal(config.getPropertyAsInteger(CommonClientConfigKey.MaxTotalConnections, 0))
```

修改配置设置该值大小
```yaml
ribbon:
   MaxTotalConnections: 200 #socket连接池最大值
   MaxConnectionsPerHost: 100 #每个路由的socket连接池最大值
```
修改后再次压测发现每过100才会翻倍。

### okhttp
简单看了下okhttp，ribbon使用的okhttp使用了Response response = httpClient.newCall(request).execute();
为同步发送请求。而与httpclient最大区别是okttp是同一ip和端口的请求重用一个socket，而httpclient是每次请求都建立socket，
所以okhttp更适合，压测性能也更高。

## 参考
- http://jinnianshilongnian.iteye.com/blog/2089792