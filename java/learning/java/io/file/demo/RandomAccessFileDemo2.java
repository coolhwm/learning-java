package learning.java.io.file.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile read demo
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class RandomAccessFileDemo2 {


    private File file;

    @Before
    public void initFile(){
        file = new File("G:\\javaindex\\raf.dat");
    }

    @Test
    public void read() throws Exception{
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        raf.seek(0);
        byte[] buff = new byte[(int) raf.length()];
        raf.read(buff);
        String s1 = new String(buff, "UTF-8");
        System.out.println(s1);
    }
}
