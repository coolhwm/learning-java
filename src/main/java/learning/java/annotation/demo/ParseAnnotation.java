package learning.java.annotation.demo;


import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ParseAnnotation {

    private static final String CLASS_FULL_NAME = "learning.java.annotation.demo.Man";

    private static Class<?> c = null;

    @BeforeClass
    public static void loadClass(){
        //使用类加载器
        try {
            c = Class.forName(CLASS_FULL_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseClassAnnotation(){
        //找到类上面的注解
        boolean isExist = c.isAnnotationPresent(Description.class);
        if (isExist) {
            //拿到注解实例
            Description d = c.getAnnotation(Description.class);
            System.out.println(d.desc());
        }
    }


    @Test
    public void parseMethodAnnotation1(){
        //拿到方法
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            boolean isExist = method.isAnnotationPresent(Description.class);
            if (isExist) {
                Description d = method.getAnnotation(Description.class);
                System.out.println(d.desc());
            }
        }
    }

    @Test
    public void parseMethodAnnotation2(){
        //拿到方法
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if(annotation instanceof  Description){
                    System.out.println(((Description) annotation).desc());
                }
            }
        }
    }

    @Test
    public void parseFiledAnnotation(){
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            boolean isExist = field.isAnnotationPresent(Description.class);
            if (isExist) {
                Description annotation = field.getAnnotation(Description.class);
                System.out.println(annotation.desc());
            }
        }
    }
}
