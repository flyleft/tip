##synchronized同步方法

- **方法的变量不存在非线程安全问题，永远都是线程安全的。**

- **实例变量为非线程安全。**

- **多个线程访问同一个对象中的同步方法时一定是线程安全的。**

- **多个对象则有多个锁。**同步方法的锁为对象锁，当存在多个对象的时候，JVM就会创建多个锁。

- **只有共享资源的读写访问需要同步化**，如果非共享资源，则根本没有同步化的必要。

- A线程先持有对象的Lock锁，B线程可以异步的方式调用该对象的非synchronized方法；A线程先持有对象的Lock锁，B线程此时调用该对象的synchronized方法，则需要等待，也就是同步。

- **脏读。**生脏读的情况是在读取实例变量时，此值已经被其他线程更改过了，脏读一定发生在操作实例变量的情况下，这就是不同线程“争抢”实例变量的结果。

```java
//测试synchronized类
public class PublicVar {
    public String username="A";
    public String password="AA";
    synchronized public void setValue(String username,String password){
        try {
            this.username=username;
            Thread.sleep(1000);
            this.password=password;
            System.out.println("thread:"+Thread.currentThread().getName()+
                    " username"+this.username+" password"+this.password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void getValue(){
        System.out.println("thread:"+Thread.currentThread().getName()+
                " username"+this.username+" password"+this.password);
    }
}
//测试线程
public class ThreadA extends Thread {
    private PublicVar publicVar;

    public ThreadA(PublicVar publicVar) {
        super();
        this.publicVar = publicVar;
    }
    @Override
    public void run() {
        super.run();
        publicVar.setValue("B","BB");
    }
}
//测试入口
public class DrMain {
    public static void main(String args[]){
        try {
            PublicVar publicVar=new PublicVar();
            ThreadA threadA=new ThreadA(publicVar);
            threadA.start();
            threadA.sleep(200);
            publicVar.getValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
```
结果：
thread:main usernameB passwordAA  //发生了脏读
thread:Thread-0 usernameB passwordBB
```

