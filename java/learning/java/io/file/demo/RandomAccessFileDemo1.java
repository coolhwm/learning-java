package learning.java.io.file.demo;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile write demo
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class RandomAccessFileDemo1 {

    private File file;

    @Before
    public void initFile(){
        try {
            file = new File("G:\\javaindex\\raf.dat");
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeChinese() throws Exception{
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        String str = "ABS中";
        byte[] bytes = str.getBytes("UTF-8");
        raf.write(bytes);

        //UTF-16BE
        //raf.writeChars("测试本文123");
    }

    @Test
    public void writeInteger() throws Exception{
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        //7F FF FF FF
        int i = Integer.MAX_VALUE;
        raf.writeInt(i);
    }

    @Test
    public void writeMaxInteger() throws Exception{
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        //7F FF FF FF
        int i = Integer.MAX_VALUE;
        raf.write(i >>> 24);
        raf.write(i >>> 16);
        raf.write(i >>> 8);
        raf.write(i);
        System.out.println(raf.getFilePointer());
    }


    @Test
    public void writeChar() throws Exception{
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        System.out.println(raf.getFilePointer());
        raf.write('A');
        System.out.println(raf.getFilePointer());
        raf.write('B');
        System.out.println(raf.getFilePointer());
    }



}
