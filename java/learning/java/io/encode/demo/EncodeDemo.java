package learning.java.io.encode.demo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;

@RunWith(BlockJUnit4ClassRunner.class)
public class EncodeDemo {

    @Test
    public void encodeConvert() throws Exception{
        String s1 = "慕课ABC";
        byte[] bytes = s1.getBytes("UTF-8");

        //error encode
        String s2 = new String(bytes, "GBK");
        System.out.println(s2);

        //right encode
        String s3 = new String(bytes, "UTF-8");
        System.out.println(s3);

        //convert to right encode from error encode
        String s4 = new String(s2.getBytes("GBK"), "UTF-8");
        System.out.println(s4);
    }


    @Test
    public void encodeBytes(){
        String s = "慕课ABC";
        printStringBytes(s, "GBK");
        printStringBytes(s, "GB2312");
        printStringBytes(s, "UTF-8");
        printStringBytes(s, "UTF-16BE");
        printStringBytes(s, "UTF-16");
        printStringBytes(s, "Unicode");
        printStringBytes(s, "ISO-8859-1");
        printStringBytes(s, "ASCII");
    }

    private void printStringBytes(String str, String encode){
        try {
            System.out.println("--- String [" + str + "] to " + encode + " bytes ---" );
            byte[] bytes = str.getBytes(encode);
            for (byte b : bytes) {
                //to hexadecimal
                System.out.print(Integer.toHexString(b & 0xff) + " ");
            }
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
