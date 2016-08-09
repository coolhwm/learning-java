package learning.java.io.serialize.demo;


import org.junit.Test;

import java.io.*;

public class SerializeDemo2 {

    @Test
    public void writeInheritedSerialize() throws IOException {
        File file = new File("E:/data.dat");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
       /* Foo2 foo2 = new Foo2();
        oos.writeObject(foo2);*/

        Bar2 bar2 = new Bar2();
        oos.writeObject(bar2);
    }


    @Test
    public void readInheritedSerialize() throws IOException, ClassNotFoundException {
        File file = new File("E:/data.dat");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object o = ois.readObject();
        System.out.println(o);
    }

}

class Foo implements Serializable{
    public Foo(){
        System.out.println("Foo...");
    }
}

class Foo1 extends Foo{
    public Foo1() {
        System.out.println("Foo1...");
    }
}

class Foo2 extends Foo1{
    public Foo2() {
        System.out.println("Foo2...");
    }
}

class Bar {
    public Bar(){
        System.out.println("Bar...");
    }
}

class Bar1 extends Bar {
    public Bar1() {
        System.out.println("Bar1...");
    }
}

class Bar2 extends Bar1 implements Serializable {
    public Bar2() {
        System.out.println("Bar2...");
    }
}