package learning.java.reflect.method.demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodDemo1 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        A a1 = new A();
        //get Class Type
        Class<? extends A> c = a1.getClass();

        System.out.println("----call print(int,int)----");
        //the Method object that matches the specified name and parameterTypes
        Method m1 = c.getMethod("print", int.class, int.class);
        //instance invoke
        a1.print(1,2);
        //reflect invoke
        Object result1 = m1.invoke(a1, 1, 2);

        System.out.println("----call print(String,String)----");
        Method m2 = c.getMethod("print", String.class, String.class);
        Object result2 = m2.invoke(a1, "Hello", "World");

        System.out.println("----call print()----");
        Method m3 = c.getMethod("print");
        Object result3 = m3.invoke(a1);

    }
}

class A{
    public void print(){
        System.out.println("Hello World!!!");
    }

    public void print(int a, int b){
        System.out.println(a + b);
    }

    public void print(String a, String b){
        System.out.println(a.toUpperCase() + "," + b.toLowerCase());

    }
}
