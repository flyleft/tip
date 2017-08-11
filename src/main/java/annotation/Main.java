package annotation;

import java.lang.annotation.Annotation;

public class Main {
    public static void main(String[] args) throws Exception{
        Class<?> c=Class.forName("annotation.Test");
        Annotation annotation=c.getAnnotation(Desc.class);
        if (annotation!=null){
           Desc desc=(Desc)annotation;
           System.out.println("value:"+desc.value());
           System.out.println("description:"+desc.desc());
        }
    }
}
