package learning.java.concurrent.deamon;


import java.io.*;

class DeamonThread implements Runnable{
    @Override
    public void run() {
        System.out.println("进入守护线程:" + Thread.currentThread().getName());
        writeToFile();
        System.out.println("退出守护线程：" + Thread.currentThread().getName());
    }

    private void writeToFile(){
        try {
            File file = new File("E:/1.txt");
            OutputStream fos = new FileOutputStream(file,true);
            for (int i = 0; i < 999; i++) {
                fos.write(("\n\r word" + i).getBytes());
                System.out.println("守护线程写入word" + i);
                Thread.sleep(100);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class DeamonDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("程序进入了主线程" + Thread.currentThread().getName());
        DeamonThread r = new DeamonThread();
        Thread t = new Thread(r);
        t.setDaemon(true);
        t.start();
        Thread.sleep(100000);
        System.out.println("程序退出了主线程" + Thread.currentThread().getName());
    }
}
