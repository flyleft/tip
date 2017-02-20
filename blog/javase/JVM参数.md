### 1. -server and -client

服务端的 VM 中的默认为堆提供了一个更大的空间以及一个并行的垃圾收集器，并且在运行时可以更大程度地优化代码。

客户端的 VM 更加保守一些，这样可以缩短 JVM 的启动时间和占用更少的内存。

### 2. -version and -showversion

-version 参数在打印完上述信息后立即终止 JVM。

-showversion 可以用来输出相同的信息，但是 - showversion 紧接着会处理并执行 Java 程序。

### 3. -Xint, -Xcomp, 和 -Xmixed

-Xint 标记会强制 JVM 执行所有的字节码，当然这会降低运行速度，通常低 10 倍或更多。

-Xcomp 参数与它（-Xint）正好相反，JVM 在第一次使用时会把所有的字节码编译成本地代码，也会有一些性能损失

-Xmixed HotSpot 的默认模式是混合模式

### 4. -XX:+PrintCompilation and -XX:+CITime

-XX:+PrintCompilation，简单的输出一些关于从字节码转化成本地代码的编译过程

-XX:+CITime，在 JVM 关闭时得到各种编译的统计信息

### 5. -XX:+PrintFlagsFinal and -XX:+PrintFlagsInitial

-XX:+PrintFlagsInitial: 显示所有可设置参数及默认值, 设置前的值

-XX:+PrintFlagsFinal: 显示所有可设置参数及默认值, 设置后的值

### 6. -Xms and -Xmx

-Xms： JVM 的初始堆内存大小，支持使用速记符号，比如 “k” 或者 “K” 代表 “kilo”，“m” 或者 “M” 代表 “mega”，“g” 或者 “G” 代表 “giga”。

-Xmx： JVM 的最大堆内存大小，支持使用速记符号，比如 “k” 或者 “K” 代表 “kilo”，“m” 或者 “M” 代表 “mega”，“g” 或者 “G” 代表 “giga”。

### 7. -XX:+HeapDumpOnOutOfMemoryError and -XX:HeapDumpPath

-XX:+HeapDumpOnOutOfMemoryError: 让 JVM 在发生内存溢出时自动的生成堆内存快照。默认情况下，堆内存快照会保存在 JVM 的启动目录下名为 java_pid.hprof 的文件里。

-XX:HeapDumpPath= 来改变默认的堆内存快照生成路径， 可以是相对或者绝对路径。

### 8. -XX:OnOutOfMemoryError

-XX:OnOutOfMemoryError: 当内存溢发生时，我们甚至可以可以执行一些指令，比如发个 E-mail 通知管理员或者执行一些清理工作。

### 9. -XX:PermSize and -XX:MaxPermSize

-XX:MaxPermSize 用于设置永久代大小的最大值.

-XX:PermSize 用于设置永久代初始大小。

### 10. -XX:InitialCodeCacheSize and -XX:ReservedCodeCacheSize

> “代码缓存”用来存储已编译方法生成的本地代码。如果代码缓存被占满，JVM 会打印出一条警告消息，并切换到 interpreted-only 模式：JIT 编译器被停用，字节码将不再会被编译成机器码。这俩参数用以设置代码缓存大小。

### 11. -XX:+UseCodeCacheFlushing

-XX:+UseCodeCacheFlushing：当代码缓存被填满时让 JVM 放弃一些编译代码。

### 12. -XX:NewSize and -XX:MaxNewSize

> 如果新生代过小，会导致新生对象很快就晋升到老年代中，在老年代中对象很难被回收。如果新生代过大，会发生过多的复制过程。我们需要找到一个合适大小，不幸的是，要想获得一个合适的大小，只能通过不断的测试调优。

-XX:NewSize： 设置新生代初始大小。最大可以设置为 - Xmx/2。

-XX:MaxNewSize: 设置新生代最大内存。

### 13. -XX:NewRatio

-XX:NewRatio：设置新生代和老年代的相对大小。优点是新生代大小会随着整个堆大小动态扩展。
参数 -XX:NewRatio 设置老年代与新生代的比例。例如 -XX:NewRatio=3 指定老年代 / 新生代为 3/1。 
老年代占堆大小的 3/4，新生代占 1/4 。

### 14. -XX:SurvivorRatio

-XX:SurvivorRatio 指定伊甸园区 (Eden) 与幸存区大小比例。
例如，-XX:SurvivorRatio=10表示伊甸园区是幸存区To大小的10倍(也是幸存区 From 的 10 倍)。
所以，伊甸园区占新生代大小的 10/12， 幸存区 From 和幸存区 To 每个占新生代的 1/12 。

### 15. -XX:+PrintTenuringDistribution

-XX:+PrintTenuringDistribution:指定 JVM 在每次新生代 GC 时，输出幸存区中对象的年龄分布。

### 16. -XX:InitialTenuringThreshold， -XX:MaxTenuringThreshold and -XX:TargetSurvivorRatio

-XX:InitialTenuringThreshold: 设定老年代阀值的初始值和最大值。

-XX:MaxTenuringThreshold: 设定老年代阀值的最大值。

-XX:TargetSurvivorRatio 设定幸存区的目标使用率。
例如，-XX:MaxTenuringThreshold=10 -XX:TargetSurvivorRatio=90设定老年代阀值的上限为 10,幸存区空间目标使用率为 90%。

### 17. -XX:+NeverTenure and -XX:+AlwaysTenure

-XX:+NeverTenure，对象永远不会晋升到老年代。当我们确定不需要老年代时，可以这样设置。这样设置风险很大，并且会浪费至少一半的堆内存。

-XX:+AlwaysTenure，表示没有幸存区，所有对象在第一次 GC 时，会晋升到老年代。

### 18. -XX:+UseSerialGC

-XX:+UseSerialGC: 来激活串行垃圾收集器，例如单线程面向吞吐量垃圾收集器。
无论年轻代还是年老代都将只有一个线程执行垃圾收集。 该标志被推荐用于只有单个可用处理器核心的 JVM。
 
### 19. -XX:+UseParallelGC

-XX:+UseParallelGC: 使用多线程并行执行年轻代垃圾收集

### 20. -XX:+UseParallelOldGC

-XX:+UseParallelOldGC: 除了激活年轻代并行垃圾收集，也激活了年老代并行垃圾收集。
当期望高吞吐量，并且JVM有两个或更多可用处理器核心时，建议使用该标志。

### 21. -XX:ParallelGCThreads

-XX:ParallelGCThreads:可以指定并行垃圾收集的线程数量。
例如，-XX:ParallelGCThreads=6 表示每次并行垃圾收集将有6个线程执行。
如果不明确设置该标志，虚拟机将使用基于可用 (虚拟) 处理器数量计算的默认值。
 
### 22. -XX:+UseAdaptiveSizePolicy
-XX:+UseAdaptiveSizePolicy：并行收集器会自动选择年轻代区大小和相应的Survivor区比例，
以达到目标系统规定的最低响应时间或者收集频率等，此值建议使用并行收集器时，一直打开。

### 23. -XX:GCTimeRatio
-XX:GCTimeRatio: 告诉 JVM 吞吐量要达到的目标值。
更准确地说，-XX:GCTimeRatio=N 指定目标应用程序线程的执行时间 (与总的程序执行时间) 达到 N/(N+1) 的目标比值。

### 24. -XX:MaxGCPauseMillis

-XX:GCTimeRatio: 告诉JVM最大暂停时间的目标值(以毫秒为单位)。默认情况下，最大暂停时间没有被设置。 
如果最大暂停时间和最小吞吐量同时设置了目标值，实现最大暂停时间目标具有更高的优先级。 
当然，无法保证 JVM 将一定能达到任一目标，即使它会努力去做。

### 25. -XX：+UseConcMarkSweepGC

-XX：+UseConcMarkSweepGC 激活 CMS 收集器。

### 26. -XX：UseParNewGC

-XX：UseParNewGC 当使用 CMS 收集器时，该标志激活年轻代使用多线程并行执行垃圾回收。

### 27. -XX：+CMSConcurrentMTEnabled

当该标志被启用时，并发的 CMS 阶段将以多线程执行 (因此，多个 GC 线程会与所有的应用程序线程并行工作)。该标志已经默认开启

### 28. -XX：ConcGCThreads

定义并发 CMS 过程运行时的线程数。比如 value=4 意味着 CMS 周期的所有阶段都以 4 个线程来执行。

### 29. -XX:+PrintGC

开启了简单 GC 日志模式，为每一次新生代（young generation）的 GC 和每一次的 Full GC 打印一行信息

### 30. -XX:PrintGCDetails

开启了详细 GC 日志模式。

### 31. -XX:+PrintGCTimeStamps 和 - XX:+PrintGCDateStamps

可以将时间和日期也加到 GC 日志中。表示自 JVM 启动至今的时间戳会被添加到每一行中。

### 32. -Xloggc

缺省的 GC 日志时输出到终端的，使用 - Xloggc: 也可以输出到指定的文件。
需要注意这个参数隐式的设置了参数 - XX:+PrintGC 和 - XX:+PrintGCTimeStamps。

---

## 来源： 
 - https://blog.codecentric.de/en/2014/01/useful-jvm-flags-part-8-gc-logging/