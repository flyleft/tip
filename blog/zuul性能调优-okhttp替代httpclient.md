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

httpclient的TCP连接获取代码位于AbstractConnPool.java

```java
    private E getPoolEntryBlocking(
            final T route, final Object state,
            final long timeout, final TimeUnit tunit,
            final PoolEntryFuture<E> future)
                throws IOException, InterruptedException, TimeoutException {

        Date deadline = null;
        // 设置连接超时时间戳
        if (timeout > 0) {
            deadline = new Date
                (System.currentTimeMillis() + tunit.toMillis(timeout));
        }
        // 获取连接，并修改修改连接池，所以加锁--->线程安全
        this.lock.lock();
        try {
            // 从Map中获取该route对应的连接池，若Map中没有，则创建该route对应的连接池
            final RouteSpecificPool<T, C, E> pool = getPool(route);
            E entry = null;
            while (entry == null) {
                Asserts.check(!this.isShutDown, "Connection pool shut down");
                for (;;) {
                    // 获取 同一状态的 空闲连接，即从available链表的头部中移除，添加到leased集合中
                    entry = pool.getFree(state);
                    // 若返回连接为空，跳出循环
                    if (entry == null) {
                        break;
                    }
                    // 若连接已过期，则关闭连接
                    if (entry.isExpired(System.currentTimeMillis())) {
                        entry.close();
                    } else if (this.validateAfterInactivity > 0) {
                        if (entry.getUpdated() + this.validateAfterInactivity <= System.currentTimeMillis()) {
                            if (!validate(entry)) {
                                entry.close();
                            }
                        }
                    }
                    if (entry.isClosed()) {
                        // 若该连接已关闭，则总的available链表中删除该连接
                        this.available.remove(entry);
                        // 从该route对应的连接池的leased集合中删除该连接，并且不回收到available链表中                        
                        pool.free(entry, false);
                    } else {
                        break;
                    }
                }
                // 跳出for循环
                if (entry != null) {
                    // 若获取的连接不为空，将连接从总的available链表移除，并添加到leased集合中
                    // 获取连接成功，直接返回
                    this.available.remove(entry);
                    this.leased.add(entry);
                    onReuse(entry);
                    return entry;
                }
                // 计算该route的最大连接数
                // New connection is needed
                final int maxPerRoute = getMax(route);
                // Shrink the pool prior to allocating a new connection
                  // 计算该route连接池中的连接数 是否 大于等于 route最大连接数
                final int excess = Math.max(0, pool.getAllocatedCount() + 1 - maxPerRoute);
                // 若大于等于 route最大连接数，则收缩该route的连接池
                if (excess > 0) {
                    for (int i = 0; i < excess; i++) {
                        // 获取该route连接池中最不常用的空闲连接，即available链表末尾的连接
                        // 因为回收连接时，总是将连接添加到available链表的头部，所以链表尾部的连接是最有可能过期的
                        final E lastUsed = pool.getLastUsed();
                        if (lastUsed == null) {
                            break;
                        }
                        // 关闭连接，并从总的空闲链表以及route对应的连接池中删除
                        lastUsed.close();
                        this.available.remove(lastUsed);
                        pool.remove(lastUsed);
                    }
                }
                // 该route的连接池大小 小于 route最大连接数
                if (pool.getAllocatedCount() < maxPerRoute) {
                    final int totalUsed = this.leased.size();
                    final int freeCapacity = Math.max(this.maxTotal - totalUsed, 0);
                    if (freeCapacity > 0) {
                        final int totalAvailable = this.available.size();
                        // 总的空闲连接数 大于等于 总的连接池剩余容量
                        if (totalAvailable > freeCapacity - 1) {
                            if (!this.available.isEmpty()) {
                                // 从总的available链表中 以及 route对应的连接池中 删除连接，并关闭连接
                                final E lastUsed = this.available.removeLast();
                                lastUsed.close();
                                final RouteSpecificPool<T, C, E> otherpool = getPool(lastUsed.getRoute());
                                otherpool.remove(lastUsed);
                            }
                        }
                        // 创建新连接，并添加到总的leased集合以及route连接池的leased集合中，函数返回
                        final C conn = this.connFactory.create(route);
                        entry = pool.add(conn);
                        this.leased.add(entry);
                        return entry;
                    }
                }
                
                //route的连接池已满，无法分配连接
                boolean success = false;
                try {
                    // 将该获取连接的任务放入pending队列
                    pool.queue(future);
                    this.pending.add(future);
                    // 阻塞等待，若在超时之前被唤醒，则返回true；若直到超时才返回，则返回false
                    success = future.await(deadline);
                } finally {
                    // 无论是 被唤醒返回、超时返回 还是被 中断异常返回，都会进入finally代码段
                    // 从pending队列中移除
                    pool.unqueue(future);
                    this.pending.remove(future);
                }
                // 判断是伪唤醒 还是 连接超时
                // 若是 连接超时，则跳出while循环，并抛出 连接超时的异常；
                // 若是 伪唤醒，则继续循环获取连接
                if (!success && (deadline != null) &&
                    (deadline.getTime() <= System.currentTimeMillis())) {
                    break;
                }
            }
            throw new TimeoutException("Timeout waiting for connection");
        } finally {
            // 释放锁
            this.lock.unlock();
        }
    }
```

因为同一个route的连接数有限制，最大值为DefaultMaxPerRoute，超过则进入pending队列，直到调用release释放，有空闲连接才会继续执行，
就是次数造成了"50翻倍"问题。

### okhttp
简单看了下okhttp，ribbon使用的okhttp使用了Response response = httpClient.newCall(request).execute();
为同步发送请求。而与httpclient最大区别是okttp是同一ip和端口的请求重用一个socket。

okhttp获取socket连接代码主要位于StreamAllocation的findConnection方法

```java
private RealConnection findConnection(int connectTimeout, int readTimeout, int writeTimeout,
      boolean connectionRetryEnabled) throws IOException {
    Route selectedRoute;
    synchronized (connectionPool) {
      if (released) throw new IllegalStateException("released");
      if (codec != null) throw new IllegalStateException("codec != null");
      if (canceled) throw new IOException("Canceled");

      // 尝试使用已经建立好的连接
      RealConnection allocatedConnection = this.connection;
      if (allocatedConnection != null && !allocatedConnection.noNewStreams) {
        return allocatedConnection;
      }

      Internal.instance.get(connectionPool, address, this, null);
      if (connection != null) {
        return connection;
      }

      selectedRoute = route;
    }

    if (selectedRoute == null) {
      selectedRoute = routeSelector.next();
    }

    RealConnection result;
    synchronized (connectionPool) {
      if (canceled) throw new IOException("Canceled");

     
      // 通过address尝试从连接池获取一个已经建立的socket连接
      Internal.instance.get(connectionPool, address, this, selectedRoute);
      if (connection != null) {
        route = selectedRoute;
        return connection;
      }
      route = selectedRoute;
      refusedStreamCount = 0;
      
      //没有则创建一个新的socket连接
      result = new RealConnection(connectionPool, selectedRoute);
      acquire(result);
    }

    // 完成TCP + TLS 握手
    result.connect(connectTimeout, readTimeout, writeTimeout, connectionRetryEnabled);
    routeDatabase().connected(result.route());

    Socket socket = null;
    synchronized (connectionPool) {
      // Pool the connection.
      Internal.instance.put(connectionPool, result);

      // 如果相同的地址有多个连接，则删除只留一个
      if (result.isMultiplexed()) {
        socket = Internal.instance.deduplicate(connectionPool, address, this);
        result = connection;
      }
    }
    closeQuietly(socket);
    return result;
  }
```

okhttp如果地址相同则直接复用socket连接，且ribbon的okhttp的socket连接数没有设置限制。


## 参考
- http://jinnianshilongnian.iteye.com/blog/2089792
- https://segmentfault.com/a/1190000012009507