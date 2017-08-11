##synchronized同步方法

- **方法的变量不存在非线程安全问题，永远都是线程安全的。**

- **实例变量为非线程安全。**

- **多个对象则有多个锁。**非静态同步方法的锁为对象锁，当存在多个对象的时候，JVM就会创建多个锁。

- **锁对象。**
  1. 所有的非静态同步方法用的都是同一把锁——实例对象本身
  > 也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其他非静态同步方法必须等待获取锁的方法释放锁后才能获取锁，可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同的锁，所以毋须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。

  2. 所有的静态同步方法用的也是同一把锁——类对象本身
  > 静态同步方法与非静态同步方法之间是不会有竞态条件的。但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，而不管是同一个实例对象的静态同步方法之间，还是不同的实例对象的静态同步方法之间，只要它们同一个类的实例对象！

- **只有共享资源的读写访问需要同步化，**如果非共享资源，则根本没有同步化的必要。

- A线程先持有对象的Lock锁，B线程可以异步的方式调用该对象的非synchronized方法；A线程先持有对象的Lock锁，B线程此时调用该对象的synchronized方法，则需要等待，也就是同步。

- **同步不可以继承。**要想子类的方法同步，也需要加上synchronized关键字。

- **出现异常，自动释放锁。**当一个线程执行的代码出现异常时，其所持有的锁会自动释放。

- **锁重入。**在一个synchronized方法/块内部调用本类的其他的synchronized方法/块时，是永远可以得到锁的；当存在父子类继承关系时，子类完全可以通过"可重入锁"调用父类的同步方法的。

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

##synchronized同步代码块

- synchronized方法是对当前对象加锁，而synchronized同步代码块是对某一个对象进行加锁。

- 在synchronized同步代码块中的就是同步执行，不在就是异步执行。

- 静态同步synchronized方法和synchronized(class)代码块的锁都是*.java文件对应的Class类进行持锁。

- 同步synchronized代码块不使用String作为锁对象，因为String的常量池的影响，而改用其他，比如new Object()实例化对象

#####参考:
- JAVA多线程编程核心技术
- JAVA编程思想