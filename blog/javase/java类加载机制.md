- ### 类加载大致过程
java中的类只有被JVM加载之后才能在程序中使用，加载的过程大致为加载-连接-初始化，其中连接又分为验证-准备-解析。
所以细分为**加载-验证-准备-解析-初始化**五个阶段。
其中加载、验证、准备和初始化这四个阶段发生的顺序是确定的，而解析阶段则不一定，有时为了动态绑定也会以在初始化阶段之后开始。

- ### 类加载的条件
JVM肯定不会无缘无故就去加载.class，只有主动使用时才会初始化。主动使用有以下几种情况：
 1. 当创建一个类的实例时，比如使用new或者反射，克隆，反序列化。
 2. 当调用类的静态方法时，即使用了字节码invokestatic指令。
 3. 当时用类或接口的静态字段时(final常量除外)，即使用了字节码的getstatic和putstatic指令。
 4. 当使用了java.lang.reflect包中的方法反射类的方法时。
 5. 当初始化子类时，要求先初始化父类。
 6. 作为启动虚拟机，含有main()方法那个类。
 ```java
 public class Parent{
   static{
   	System.out.println("parent init");
   }
   public static int v=100;
 }
 public class Child extends Parent{
  static{
    System.out.println("child init");
  }
 }
 public class Main{
  public static void main(String args[]){
    System.out.println(Child.v)
  }
 }
 ```
 运行输出：
 ```
 parent init
 100
 ```
 可以看到只有父类被初始化，子类没有。可见，引用一个子类时(例子中的v)，只有直接定义该字段的类(例子中的父类)，才会被初始化。注意，child虽然没有被初始化但是已经被加载。

- ### 类加载的过程-加载



