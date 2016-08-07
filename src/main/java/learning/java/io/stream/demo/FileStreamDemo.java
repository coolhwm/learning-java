package learning.java.io.stream.demo;

import learning.java.io.stream.utils.IOUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RunWith(BlockJUnit4ClassRunner.class)
public class FileStreamDemo {

    @Test
    public void copy() throws  Exception{
        File src = new File("F:/nohup.out");
        File dest = new File("F:/copy.out");
        IOUtil.copyFile(src,dest);
    }


    @Test
    public void copyBuffered() throws  Exception{
        File src = new File("F:/nohup.out");
        File dest = new File("F:/copy.out");
        IOUtil.copyFileByBuffer(src,dest);
    }


    @Test
    public void out() throws IOException{
        OutputStream fos = new FileOutputStream("F:/out.data");
        fos.write('a');
        fos.close();
        IOUtil.printHex("F:/out.data");
    }


    @Test
    public void read() throws Exception{
        long beginTime = System.currentTimeMillis();
        IOUtil.printHex("F:/nohup.out");
        System.out.println(System.currentTimeMillis() - beginTime);
    }


    @Test
    public void readByByte() throws Exception{
        long beginTime = System.currentTimeMillis();
        IOUtil.printHexByByteArray("F:/nohup.out");
        System.out.println(System.currentTimeMillis() - beginTime);
    }
}
