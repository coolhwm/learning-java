package learning.java.reflect.demo;


public class ClassDemo1 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //instance
        Foo foo = new Foo();

        //class instance

        // .class
        Class<Foo> c1 = Foo.class;

        // getClass
        Class<? extends Foo> c2 = foo.getClass();

        //is equals
        System.out.println(c1 == c2);

        Class<?> c3 = Class.forName("learning.java.reflect.demo.Foo");

        //is equals
        System.out.println(c1 == c3);

        // new instance by Class Type
        Foo f1 = c1.newInstance();
        f1.print();
    }

}
class Foo{
    void print(){
        System.out.println("foo");
    }
}