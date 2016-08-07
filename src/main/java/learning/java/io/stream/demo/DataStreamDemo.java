package learning.java.io.stream.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@RunWith(BlockJUnit4ClassRunner.class)
public class DataStreamDemo {

    @Test
    public void dos() throws Exception{
        String file = "F:/dos.data";
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
        dos.writeUTF("中国");
    }

    @Test
    public void dis() throws Exception{
        String file = "F:/dos.data";
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        String result = dis.readUTF();
        System.out.println(result);
    }
}
