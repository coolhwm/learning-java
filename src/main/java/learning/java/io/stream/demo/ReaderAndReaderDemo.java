package learning.java.io.stream.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

@RunWith(BlockJUnit4ClassRunner.class)
public class ReaderAndReaderDemo {

    @Test
    public void dos() throws Exception{
        String file = "F:/nohup.out";
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("F:/copy.out"), "UTF-8");
        char[] buffer = new char[2*1024];
        int c;
        while((c = isr.read(buffer, 0, buffer.length)) != -1){
            String str = new String(buffer, 0, c);
            osw.write(buffer,0,c);
            System.out.println(str);
        }

    }
}
