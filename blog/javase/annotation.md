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

- ####示例
```java
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Desc {
    String author();
    String time();
    String desc() default "描述：";
}
```

- #### 注意事项
1. 注解可以设定初始值，使用default就可以实现。
2. 注解只有一个元素的时候，该元素名称必须是value，并且在使用该注解的时候可以省略”alue=”。
3. 注解的值必须是确定的，且不能使用null作为值。
4. 注解可能的类型：所有基本类型、String、Class、enum、Annotation、以上类型的数组形式。




