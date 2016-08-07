package learning.java.io.stream.utils;

import java.io.*;

public class IOUtil {

    public static void copyFileByBuffer(File srcFile, File destFile) throws Exception{
        if(!srcFile.exists()){
            throw new IllegalArgumentException("file is not exists!");
        }
        if(!srcFile.isFile()){
            throw new IllegalArgumentException("file is not file");
        }
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
        long startTime = System.currentTimeMillis();
        int c;
        while((c = bis.read()) != -1){
            bos.write(c);
            bos.flush();
        }
        System.out.println("copy end ----> " + (System.currentTimeMillis() - startTime));
        bis.close();
        bos.close();
    }


    public static void copyFile(File srcFile, File destFile) throws Exception{
        if(!srcFile.exists()){
            throw new IllegalArgumentException("file is not exists!");
        }
        if(!srcFile.isFile()){
            throw new IllegalArgumentException("file is not file");
        }
        InputStream fis = new FileInputStream(srcFile);
        OutputStream fos = new FileOutputStream(destFile);
        byte[] buff = new byte[2 * 2014];
        int read;
        long startTime = System.currentTimeMillis();
        while((read = fis.read(buff, 0, buff.length)) != -1){
            fos.write(buff,0,read);
            fos.flush();
        }
        System.out.println("copy end ----> " + (System.currentTimeMillis() - startTime));
        fos.close();
        fis.close();
    }


    public static void printHexByByteArray(String fileName) throws IOException {
        InputStream fis = new FileInputStream(fileName);
        byte[] buff = new byte[20 * 1024];
        int bytes;
        int j = 0;
        while((bytes = fis.read(buff,0,buff.length)) != -1){
            for (int i = 0; i < bytes; i++) {
                if(buff[i] < 0xf){
                    System.out.print("0");
                }
                System.out.print(Integer.toHexString(buff[i]) + " ");
                if(j++ % 10 == 0){
                    System.out.println();
                }
            }
        }
    }

    /**
     *  read file and print hex to console, every 10 byte print a \n
     */
    public static void printHex(String fileName) {
        InputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            int b;
            int i = 0;
            while((b=fis.read()) != -1){
                if(b <= 0xf){
                    System.out.print("0" + Integer.toHexString(b) + " ");
                }
                else{
                    System.out.print(Integer.toHexString(b) + " ");
                }

                if(i++ % 10 == 0){
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
