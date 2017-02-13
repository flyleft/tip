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
