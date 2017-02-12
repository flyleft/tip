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