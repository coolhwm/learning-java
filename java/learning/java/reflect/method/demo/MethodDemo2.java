package learning.java.reflect.method.demo;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodDemo2 {


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> list1 = new ArrayList<String>();
        List<Integer> list2 = new ArrayList<Integer>();

        Class<? extends List> c1 = list1.getClass();
        Class<? extends List> c2 = list2.getClass();

        System.out.println(c1 == c2);

        Method m1 = c1.getMethod("add", Object.class);

        m1.invoke(list1,1);

        System.out.println(list1.size());


    }
}
