package learning.java.reflect.demo;

public class ClassDemo2 {
    public static void main(String[] args) {
        // int Class Type
        Class c1 = int.class;

        //Integer Class Tyep
        Class c2 = Integer.class;

        //false
        System.out.println(c1 == c2);

        //String Class Type(byte-code)
        Class<String> c3 = String.class;

        Class c4 = void.class;

        //full name
        System.out.println(c2.getName());
        //simple name
        System.out.println(c2.getSimpleName());
    }
}
