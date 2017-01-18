##注解分类

1. 源码注解： 只在源码中存在，编译成.class就不存在了。

2. 编译时注解： 源码和.class中都存在，比如@Override，@Deprecated等

3. 运行时注解： 在运行时注解，比如spring的@Component，@AutoWired等



##标准注解

  | 注解标识 | 作用 |
  | :-------------- | :------------ |
  | @Override | 覆盖超类中的方法 |
  | @Deprecated | 声明过期 |
  | @SuppressWarnings | 关闭不当的编译器警告信息 |

## 自定义注解

- #####元注解
  > 用于自定义注解

1. 元注解类型
   - @Target
   - @Retention
   - @Documented
   - @Inherited
2. @Target
   > 定义注解将应用于什么地方,当注解未指定Target值时，此注解可以使用任何元素之上,取值(ElementType)有:

   - ***TYPE***: 接口、类、枚举、注解

   - ***FIELD***: 字段、枚举的常量

   - ***METHOD***: 方法

   - ***PARAMETER***: 方法参数

   - ***CONSTRUCTOR***: 构造器

   - ***LOCAL_VARIABLE***: 局部变量

   - ***ANNOTATION_TYPE***: 注解类型

   - ***PACKAGE***: 包

   - ***TYPE_PARAMETER***: 类型参数声明(1.8新增)。 比如public class MyList<@MySet T> {}，在定义@MySet，必须在MySet的@Target设置 ElementType.TYPE_PARAMETER ，表示这个注解可以用来标注类型参数。

   - ***TYPE_USE***: 类型使用声明(1.8新增)。只要是类型名称，都可以进行注解，以下的使用注解都是可以的:
   ```
    List<@Test Comparable> list1 = new ArrayList<>();
    List<? extends Comparable> list2 = new ArrayList<@Test Comparable>();
    @Test String text;
    text = (@Test String)new Object();
    java.util. @Test Scanner console;
    console = new java.util.@Test Scanner(System.in);
   ```

3. @Retention
   > 定义注解在哪一个级别可用，当注解未定义Retention值时，默认值是CLASS，在源代码中，类文件中或者运行时，取值(RetentionPoicy)有：

   - SOURCE ：注解将被编译器丢弃（该类型的注解信息只会保留在源码里，源码经过编译后，注解信息会被丢弃，不会保留在编译好的class文件里)

   - CLASS ：注解在class文件中可用，但会被JVM丢弃（该类型的注解信息会保留在源码里和class文件里，在执行的时候，不会加载到虚拟机（JVM）中）

   - RUNTIME ：JVM将在运行期也保留注解信息，因此可以通过反射机制读取注解的信息（源码、class文件和执行的时候都有注解的信息）

4. @Documented
   > 用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化。是一个标记注解，没有成员。

5. @Inherited
   > 阐述了某个被标注的类型是被继承的。如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。是一个标记注解，没有成员。

- ####示例

```java
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Desc {
    String value();
    String desc() default "description：";
}
```

- #### 注意事项

1. 注解可以设定初始值，使用default就可以实现。

2. 注解只有一个元素的时候，该元素名称必须是value，并且在使用该注解的时候可以省略”value=”。

3. 注解的值必须是确定的，且不能使用null作为值。

4. 注解可能的类型：
  - 基本类型（int,float,boolean,byte,double,char,long,short）
  - String
  - Class
  - enum
  - Annotation

  > 以上类型的数组 如果使用了其他类型，那编译器就会报错。也不允许使用任何包装类型。注解也可以作为元素的类型，也就是注解可以嵌套。 元素的修饰符，只能用 public 或 default

- ####编写注解解析器
  > 要想注添加逻辑，需要反射或者字节码操作获取注解信息

  1. 被注解的测试类
   ```java
   @Desc(value = "test",desc = "Test is a type")
    public class Test {
   }
   ```
  2. 反射获取注解信息
  ```java
  public class Main {
    public static void main(String[] args) throws Exception{
        Class<?> c=Class.forName("me.jcala.tip.annotation.Test");
        Annotation annotation=c.getAnnotation(Desc.class);
        if (annotation!=null){
           Desc desc=(Desc)annotation;
           System.out.println("value:"+desc.value());
           System.out.println("description:"+desc.desc());
        }
     }
   }
 }
  ```

#####[示例代码地址](https://github.com/jcalaz/tip/tree/master/src/main/java/me/jcala/tip/annotation)

#####参考:
- 慕课网
- java编程思想
- http://www.open-open.com/lib/view/open1453426805042.html