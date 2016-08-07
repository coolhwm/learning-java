package learning.java.io.stream.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class BrAndBwOrPwDemo {
    @Test
    public void test() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:/nohup.out"), "GBK"));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("F:/copy.out"), "GBK"));

        PrintWriter pw = new PrintWriter("F:/copy2.out");

        String line;
        while((line = br.readLine()) != null){
            System.out.println(line);
            bw.write(line);
            bw.newLine();

            pw.println(line);
            pw.flush();
        }

        br.close();
        bw.close();
        pw.close();
    }
}
