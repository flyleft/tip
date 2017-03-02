#### 什么是join?

如果一个线程A执行了thread.join()语句，
其含义是：当前线程A等待thread线程终止之后才能从thread.join()返回。
线程Thread除了提供join()方法之外，还提供了join(long millis)和join(long millis,int nanos)两个具备超时特性的方法。

#### 什么时候需要join呢？

在很多情况下，主线程创建并启动子线程，如果子线程需要大量耗时计算，则主线程往往早于子线程结束。
可是如果需要主线程晚于子线程结束，比如主线程需要获取子线程运算结果，就需要用到join()方法了。

#### 简单的join使用

```java
public class Main{
    public static void main(String args[]) throws InterruptedException{
        Thread joinThread = new Thread(()-> System.out.println("join thread,"));
        joinThread.start();
        joinThread.join();
        System.out.println("main thread,");
    }
}
```

输出: join thread,main thread,

#### join过程中，当前线程中断，则当前线程出现异常。
```java
public class ThreadA extends Thread{
    @Override
    public void run(){
      for(int i=0;i<Integer.MAX_VALUE;i++){
        String newString=new String();
        Math.random();
      }
    }
}
public class Main{
 public static void main(String args[]){
   Thread threadB=new Thread(
           ()->{
               try{
               ThreadA a=new ThreadA();
               a.start();
               a.join();
               System.out.println("B rum end");
               }catch (InterruptedException e){
               System.out.println("B catch exception");
               }
           }
   );
   threadB.start();
   Thread.sleep(1000);
   threadB.interrupt();
 }
}
```
会抛出InterruptedException异常。

#### join(long millis): 等待该线程终止的时间最长为 millis 毫秒。 

与sleep(long millis)区别是：join(long millis)底层是wait实现，会释放锁，而sleep不会。



#####参考:
- 《JAVA多线程编程核心技术》
- http://www.importnew.com/14958.html?utm_source=tuicool&utm_medium=referral