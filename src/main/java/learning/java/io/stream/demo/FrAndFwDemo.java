package learning.java.io.stream.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.FileReader;
import java.io.FileWriter;

@RunWith(BlockJUnit4ClassRunner.class)
public class FrAndFwDemo {

    @Test
    public void test() throws Exception{
        FileReader fr = new FileReader("F:/nohup.out");
        FileWriter fw = new FileWriter("F:/copy.out");
        char[] buffer = new char[2 * 1024];
        int c;
        while((c = fr.read(buffer,0,buffer.length)) != -1){
            fw.write(buffer,0,c);
        }
    }

}
