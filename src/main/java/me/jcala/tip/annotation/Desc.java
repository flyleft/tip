package me.jcala.tip.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})// 表示支持注解的程序元素的种类，一些可能的值有TYPE, METHOD, CONSTRUCTOR, FIELD等等。如果Target元注解不存在，那么该注解就可以使用在任何程序元素之上。
@Retention(RetentionPolicy.RUNTIME)//注解类型保留时间的长短,有SOURCE, CLASS, 以及RUNTIME三种类型
@Inherited//表示一个注解类型会被自动继承
@Documented//添加了Documented注解，那么它的注解会成为被注解元素的公共API的一部分。
public @interface Desc {
    String author();
    String time();
    String desc() default "描述：";
}
