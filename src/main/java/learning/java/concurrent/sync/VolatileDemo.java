package learning.java.concurrent.sync;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileDemo {
    private volatile  int number = 0;

    private int getNumber(){
        return this.number;
    }

    private Lock lock = new ReentrantLock();

    private void increase(){
        //不是原子操作
        lock.lock();
        try {
            this.number ++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        VolatileDemo demo = new VolatileDemo();
        for (int i = 0; i < 1000; i++) {
            new Thread(demo::increase).start();
        }
        while(Thread.activeCount() > 2){
            Thread.yield();
            System.out.println("active:" + Thread.activeCount());
        }
        System.out.println("number:"  + demo.getNumber());
    }

}
