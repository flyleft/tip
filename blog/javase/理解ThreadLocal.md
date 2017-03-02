### 理解ThreadLocal

java中可以使用public static设置一个全局变量，所有线程都共享这个变量。
但是有时，我们需要每一个线程都有一个自己的共享变量，ThreadLocal就是一个不错的解决方案。


java8中对ThreadLocal的解释
```
This class provides thread-local variables. 
These variables differ from their normal counterparts in that each thread that accesses one 
(via its get or set method)has its own, independently initialized copy of the variable. 
ThreadLocal instances are typically private static fields in classes that wish to associate state with a
 thread (e.g., a user ID or Transaction ID).
```

对其理解：

- 每个线程单独保存这个变量，对其他线程不可见。

- ThreadLocal可以给一个初始值，而每个线程都会获得这个初始化值的一个副本，也可以new的方式为线程创建一个变量。

- ThreadLocal 不是用于解决共享变量的问题的，不是为了协调线程同步而存在，而是为了方便每个线程处理自己的状态而引入的一个机制。

### ThreadLocal的使用

- ThreadLocal几个主要操作方法
```
public T get() {...}//获取线程变量对象
public void remove() {...}//移除线程变量对象，将对线程变量对象的引用设置为null
public void set(T value) {...}//添加线程变量对象
protected T initialValue() {...}//设置初始化数据，覆盖该方法可以设置初始默认值
```

- 使用的小demo

```java
public class ThreadLocalDemo {
    
    private static ThreadLocal<Integer> local=() -> 0;//覆盖initialValue方法设置初始值。

    public static void main(String[] args) {
        Thread[] threads=new Thread[3];
        for(int i=0;i<3;i++){
            threads[i]=() -> {
                 int mun=local.get();
                 for(int j=0;j<10;j++){
                     mun=mun+1;
                 }
                 local.set(mun);
                 System.out.println(Thread.currentThread().getName()+"==="+local.get());
                };
        }
        for(Thread t:threads){
            t.start();
        }
    }
}
```

运行结果
```
Thread-3===10
Thread-2===10
Thread-1===10
```
可以看出线程之间互不影响。
 
### 实现原理

1. ThreadLocal中的静态内部类ThreadLocalMap
可以看到Entry 类继承了 WeakReference<ThreadLocal<?>>，
即每个Entry对象都有一个ThreadLocal的弱引用（作为key）。
一旦线程结束，key变为一个不可达的对象，这个Entry就可以被GC了。

```java
static class ThreadLocalMap {
        static class Entry extends WeakReference<ThreadLocal<?>> {
            Object value;
            Entry(ThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }
        }
        //...
}
```
- ThreadLocal类中有一个静态内部类ThreadLocalMap，用来存放线程变量。

- ThreadLocalMap里面有一个Entry数组，根据ThreadLocal变量本身的哈希值将线程变量的值存入散列到数组中。

2. ThreadLocal的set方法及延伸

```java
public class ThreadLocal{
    //...
     public void set(T value) {
             Thread t = Thread.currentThread();//获取当前线程对象
             ThreadLocalMap map = getMap(t);//获取当前线程内的ThreadLocalMap
             if (map != null)
                 map.set(this, value);//如果map已经设置，则直接赋值
             else
                 createMap(t, value);//还没有设置，就实例化一个ThreadLocalMap
      }
      void createMap(Thread t, T firstValue) {
              t.threadLocals = new ThreadLocalMap(this, firstValue);
          }
          
     static class ThreadLocalMap{
         private static final int INITIAL_CAPACITY = 16;
        ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) {
             table = new Entry[INITIAL_CAPACITY];//开辟一个大小为16的数组
             int i = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
             table[i] = new Entry(firstKey, firstValue);
             size = 1;
             setThreshold(INITIAL_CAPACITY);// 设置扩容的阈值
        }
        private void setThreshold(int len) {
                    threshold = len * 2 / 3;
        }
     }
   //...
}
```

3. ThreadLocalMap的set方法及延伸
```java
static class ThreadLocalMap{
private void set(ThreadLocal key, Object value) {
    Entry[] tab = table;
    int len = tab.length;
    int i = key.threadLocalHashCode & (len-1);

    // 使用的是开放地址法解决冲突，如果发生碰撞则向后查找
    // 如果得到的i位置已经有值，那么就向后一个单位尝试填充
    for (Entry e = tab[i];
         e != null;
         e = tab[i = nextIndex(i, len)]) {
        ThreadLocal k = e.get();
        // 如果是相同的key就替换，说明同一个对象中的
        // 同一个threadLocal变量
        if (k == key) {
            e.value = value;
            return;
        }
        // 因为是弱引用，ThreadLocal已经被回收，所以key就是null，将value放在这个位置
        if (k == null) {
            replaceStaleEntry(key, value, i);
            return;
        }
    }

    // 直到找到一个元素为空的位置（e == null），
    // 每新占用一个数组位置（上面都是在替换原来元素或者替换
    // 已经被移除的元素，size已经加过的）就要判断是否需要进行扩容
    tab[i] = new Entry(key, value);
    int sz = ++size;
    // 如果没有清除数组中的元素并且元素个数已经大于等于阈值threshold则进行扩容
    if (!cleanSomeSlots(i, sz) && sz >= threshold)
        rehash();
}

// 扫描数组清除陈旧的数据，但并不是全部扫描，而是log2(n)对数扫描
// 在全部扫描和不扫描之间取一个折中
private boolean cleanSomeSlots(int i, int n) {
    boolean removed = false;
    Entry[] tab = table;
    int len = tab.length;

    do {
        i = nextIndex(i, len);
        Entry e = tab[i];
        if (e != null && e.get() == null) {
            n = len;
            removed = true;
            i = expungeStaleEntry(i);
        }
    // 无符号右移一位，相当于每次除2取整
    } while ( (n >>>= 1) != 0);
    return removed;
}
}
```

### ThreadLocal内存泄漏

虽然ThreadLocal的Entry继承自WeakReference，为弱引用，但是依然可能发生内存泄漏。
可以参考：http://www.linkedkeeper.com/detail/blog.action?bid=58


## 参考
- http://www.cnblogs.com/chengxiao/p/6152824.html?utm_source=tuicool&utm_medium=referral
- http://blog.csdn.net/baidu_23086307/article/details/56674454?utm_source=tuicool&utm_medium=referral
- http://qlm.pw/2016/09/22/%E5%85%B3%E4%BA%8Ethreadlocal%E5%AF%B9%E8%B1%A1/?utm_source=tuicool&utm_medium=referral
- http://www.cnblogs.com/whoislcj/p/5811989.html?utm_source=tuicool&utm_medium=referral
- http://www.cnblogs.com/sunshine-2015/p/6072184.html?utm_source=tuicool&utm_medium=referral
- http://www.sczyh30.com/posts/Java/java-concurrent-threadlocal/?utm_source=tuicool&utm_medium=referral
- http://www.linkedkeeper.com/detail/blog.action?bid=58