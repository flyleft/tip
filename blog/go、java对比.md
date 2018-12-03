## go的劣势
- 无论是`go mod`还是`dep`都还不成熟，不如`gradle`甚至`maven`好用。优点是依赖可直接从github这些git库获取，不必非得搭建私服。
- 非常规的异常处理机制，大量的`if err != nil`。
- 生态不如java不成熟。java之所以还还如此火爆，得益于强大的JVM生态、稳定性、健壮性，go虽然在提高，目前还无法和java相比。比如日志没有   `slf4j`这种”标准库”，所以一个项目里引用其他库可能会有各种log；java的ORM无非就`hibernate`、`mybatis`、`spring data JPA`，且   都很成熟，在很多项目实践，而go的ORM感觉大都不够成熟，也很少对oracle支持的；java的目前较为标准web开发是`spring boot`，go的web框   架虽一大堆，成熟性不好说，提供的功能也要比spring boot少不少。当然go发展前景也不错，云上好多如火如荼的项目`moby(docker)`、`k8s`、   `etcd`、`consul`、`istio`是go开发的。
- go自带的库已经很强大，依旧不如java。比如自带的map也不是线程安全的，要想线程安全需要自己实现。
- 为了编译速度牺牲了很多现代语言普遍拥有的特性。不支持重载，不支持泛型，不支持方法默认参数等，也没有java的注解，python的装饰器这些(但有 struct tag，参考beego的注释路由，也可以解析注释达到注解类似功能)等。
    beego的注释实现类似注解的效果：
    ```go
    // @router /staticblock/:key [get]
    func (this *CMSController) StaticBlock() {
    }

    // @router /all/:key [get]
    func (this *CMSController) AllBlock() {
    }
    ```
    
## go的优势

- 编译速度快于java，启动速度快于java，尤其是java在线上以server模式启动时(因为JIT的编译)
- 内存略小。因为JVM的原因，java内存占用比go大，尤其spring boot占用内存挺大的。go中int32为4个字节；java中int也为4个字节，但是java需要使用大量的包装类，Integer就不止4个字节了。
- 语言简洁些。没有烦人的大括号，那么多的class，使用首字母大小写来鉴别可见性，类型推断，struct相比java bean简洁等。
- 支持函数式、也可实现面向对象编程。
- 支持指针，更为灵活。比如java方法传参为值传递，基本类型值不可变，引用类型可变；而go的传参可以传指针。
- 并发更好。java的并发基于内存共享，通过Callable和Future或者netty的Future/promise模式返回结果，基于内存共享原理简单，并发场景复杂   时处理起来麻烦。go语言CSP模型，channel通知方式实现并发线程间的数据交互，使用更轻量级的协程，并发更有优势；java也有quasar这样的纤程   库、akka这样的actor库，但都不是官方支持，使用也不是很广泛。

## 总结
- 参考[techempower的微基准测试](https://www.techempower.com/benchmarks), java、go性能相近，都不错, 尤其go的`fasthttp`，java的NIO框架`vertx`、`netty`、`undertow`。java使用最广泛`spring`，因为集成太多,比较臃肿，性能则比较一般。
- 二者都是工程化不错的语言，语言可读性都不错，但go本身简洁些，也有go fmt这些工程化工具提高了可读性。
- JVM生态强大，成熟，稳定。还有kotlin、scala、groovy、clojure等语言可以替代使用，这点go还比不了。
- java受益于JVM，也局限于JVM，偏向调用底层的一些场景，go要比JAVA更合适，调用c库也更方便。比如docker，从这方便来讲，c/c++/rust这样的语言才是go的"竞争对手"。


## 泛型
   新兴语言中scala和go算是两个极端，一个极端追求灵活、高级特性，一个极端追求编译速度、工程性而牺牲了很多语言特性。当然因为其复杂性，scala注定不会太火，但学习scala会改变编程思路，而kotlin、swift也像简化版的scala。对比下go、java、scala的泛型。
   - go
     目前没有泛型，可使用空接口替代，go2.0将提供泛型。
   - java
     ```java
     //extends指定上界
     List<? extends Fruit> flistTop = new ArrayList<Apple>();
     //super指定下界
     List<? super Apple> flistBottem = new ArrayList<Apple>();
     ```
   - scala
     ```scala
      // <: 指定上界；>:指定下界
         class Pair[T <: Comparable[T]](val first: T, val second: T) {
             def bigger = if(first.compareTo(second) > 0) first else second
         }
         
         // <% 视图界定, 可利用implicit隐式转换将实参类型转换成目标类型
         class Pair_NotPerfect[T <% Comparable[T]](val first: T, val second: T) {
             def bigger = if (first.compareTo(second) > 0) first else second 
         }
         
         // 上下文界定[T : ClassTag]：记录了当前T的类型，传入什么类型就是什么类型
         def mkArray[T : ClassTag](elems: T*) = Array[T](elems: _*)
         mkArray(42, 13).foreach(println)                //42  13 mkArray("Japan", "Brazil", "Germany").foreach(println) //"Japan", "Brazil", "Germany"
         
         //类型约束：T =:= U；T <:< U；T <%< U
         def smaller(implicit ev: T <:< Comparable[T])
         
         //逆变、协变
         //如果对A及其子类型B，满足 List[B]也符合 List[A]的子类型，那么就称为covariance(协变)，如果 List[A]是 List[B]的子类型，即与原来的父子关系正相反，则称为contravariance(逆变)
     ```