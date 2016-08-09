package learning.java.io.serialize.demo;


import learning.java.io.serialize.Student;
import org.junit.Test;

import java.io.*;

public class SerializeDemo1 {

    @Test
    public void serialize() throws IOException {
        String file = "E:/data.dat";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        Student student = new Student("100001", "张三", "20");
        oos.writeObject(student);
        oos.flush();
        oos.close();
    }

    @Test
    public void unserialize() throws IOException, ClassNotFoundException {
        String file = "E:/data.dat";
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object o = ois.readObject();
        System.out.println(o);
        ois.close();
    }
}
