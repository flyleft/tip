 #### 01.以上述代码为基础，在发生过一次FullGC后，上述代码在Heap空
 ```
 static String str0="0123456789";
 static String str1="0123456789";
 String str2=str1.substring(5);
 String str3=new String(str2);
 String str4=new String(str3.toCharArray());
 str0=null;
 
 假定str0,...,str4后序代码都是只读引用。
 Java 7中，以上述代码为基础，在发生过一次FullGC后，上述代码在Heap空间（不包括PermGen）保留的字符数为（）
 
 A. 5
 B. 10
 C. 15
 D. 20
 
 正解：C
 解析：主要存储结构信息的地方，比如方法体，同时也是存储静态变量，
      以及静态代码块的区域，构造函数，常量池，接口初始化等等 
      方法区物理上还是在堆中，是在堆的持久代里面。
      堆有年轻代 (由一个Eden区和俩个survivor区组成)，老年代，
      持久代。新创建的对象都在年轻代的Eden区，经过一次JC收集后，
      存活下来的会被复制到survivor区(一个满了，就全部移动到另外一个大的中，
      但要保证其中一个survivor为空)，经过多次JC后，
      还存活的对象就被移到老年代了。 
      持久代就是经常说的方法区里面存放类信息，常量池，方法等 
      static String str0="0123456789"; 
      static String str1="0123456789";是放在方法区里。
      也就是持久代，题目中已经说了，不包含持久代，所以剩余空间为5+5+5=15.
 ```
 
#### 2. 其最小内存值和Survivor区总大小分别是（）
```
对于JVM内存配置参数：
-Xmx10240m -Xms10240m -Xmn5120m -XXSurvivorRatio=3
,其最小内存值和Survivor区总大小分别是（）

A. 5120m，1024m
B. 5120m，2048m
C. 10240m，1024m
D. 10240m，2048m

正解 D
解析：-Xmx10240m：代表最大堆
     -Xms10240m：代表最小堆
     -Xmn5120m：代表新生代
     -XXSurvivorRatio=3：代表Eden:Survivor = 3    根据Generation-Collection算法(目前大部分JVM采用的算法)，一般根据对象的生存周期将堆内存分为若干不同的区域，一般情况将新生代分为Eden ，两块Survivor；    计算Survivor大小， Eden:Survivor = 3，总大小为5120,3x+x+x=5120  x=1024
      新生代大部分要回收，采用Copying算法，快！
      老年代 大部分不需要回收，采用Mark-Compact算法
```

#### 3.下面有关java类加载器，说法正确的是？
```
A. 引导类加载器（bootstrap class loader）：它用来加载 Java 的核心库，是用原生代码来实现的
B. 扩展类加载器（extensions class loader）：它用来加载 Java 的扩展库。
C. 系统类加载器（system class loader）：它根据 Java 应用的类路径（CLASSPATH）来加载 Java 类
D. tomcat为每个App创建一个Loader，里面保存着此WebApp的ClassLoader。需要加载WebApp下的类时，就取出ClassLoader来使用

正解：ABCD

解析：bootstrap classloader －引导（也称为原始）类加载器，
     它负责加载Java的核心类。 extension classloader －扩展类加载器，
     它负责加载JRE的扩展目录（JAVA_HOME/jre/lib/ext或者由java.ext.dirs系统属性指定的）中JAR的类包。 
     system classloader －系统（也称为应用）类加载器，它负责在JVM被启动时，加载来自在命令java中的-classpath或者java.class.path系统属性
     或者 CLASSPATH*作系统属性所指定的JAR类包和类路径。
```

#### 4. jvm中垃圾回收分为scanvenge gc和full GC，其中full GC触发的条件可能有哪些
```
A. 栈空间满
B. 年轻代空间满
C. 老年代满
D. 持久代满
E. System.gc()

正解：CDE
解析：ull GC触发的条件
   除直接调用System.gc外，触发Full GC执行的情况有如下四种。
   1. 旧生代空间不足
   旧生代空间只有在新生代对象转入及创建为大对象、大数组时才会出现不足的现象，当执行Full GC后空间仍然不足，则抛出如下错误：
   java.lang.OutOfMemoryError: Java heap space 
   为避免以上两种状况引起的FullGC，调优时应尽量做到让对象在Minor GC阶段被回收、让对象在新生代多存活一段时间及不要创建过大的对象及数组。
   2. Permanet Generation空间满
   PermanetGeneration中存放的为一些class的信息等，当系统中要加载的类、反射的类和调用的方法较多时，Permanet Generation可能会被占满，在未配置为采用CMS GC的情况下会执行Full GC。如果经过Full GC仍然回收不了，那么JVM会抛出如下错误信息：
   java.lang.OutOfMemoryError: PermGen space 
   为避免Perm Gen占满造成Full GC现象，可采用的方法为增大Perm Gen空间或转为使用CMS GC。
   3. CMS GC时出现promotion failed和concurrent mode failure
   对于采用CMS进行旧生代GC的程序而言，尤其要注意GC日志中是否有promotion failed和concurrent mode failure两种状况，当这两种状况出现时可能会触发Full GC。
   promotionfailed是在进行Minor GC时，survivor space放不下、对象只能放入旧生代，而此时旧生代也放不下造成的；concurrent mode failure是在执行CMS GC的过程中同时有对象要放入旧生代，而此时旧生代空间不足造成的。
   应对措施为：增大survivorspace、旧生代空间或调低触发并发GC的比率，但在JDK 5.0+、6.0+的版本中有可能会由于JDK的bug29导致CMS在remark完毕后很久才触发sweeping动作。对于这种状况，可通过设置-XX:CMSMaxAbortablePrecleanTime=5（单位为ms）来避免。
   4. 统计得到的Minor GC晋升到旧生代的平均大小大于旧生代的剩余空间
   这是一个较为复杂的触发情况，Hotspot为了避免由于新生代对象晋升到旧生代导致旧生代空间不足的现象，在进行Minor GC时，做了一个判断，如果之前统计所得到的Minor GC晋升到旧生代的平均大小大于旧生代的剩余空间，那么就直接触发Full GC。
   例如程序第一次触发MinorGC后，有6MB的对象晋升到旧生代，那么当下一次Minor GC发生时，首先检查旧生代的剩余空间是否大于6MB，如果小于6MB，则执行Full GC。
   当新生代采用PSGC时，方式稍有不同，PS GC是在Minor GC后也会检查，例如上面的例子中第一次Minor GC后，PS GC会检查此时旧生代的剩余空间是否大于6MB，如小于，则触发对旧生代的回收。
   除了以上4种状况外，对于使用RMI来进行RPC或管理的Sun JDK应用而言，默认情况下会一小时执行一次Full GC。可通过在启动时通过- java-Dsun.rmi.dgc.client.gcInterval=3600000来设置Full GC执行的间隔时间或通过-XX:+ DisableExplicitGC来禁止RMI调用System.gc。
```

## 习题来源或参考：
   - 牛客网