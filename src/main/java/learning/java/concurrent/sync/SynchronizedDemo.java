package learning.java.concurrent.sync;


public class SynchronizedDemo {

    private boolean ready = false;

    private int result = 0;

    private int number = 1;

    public synchronized void write(){
        ready = true;
        Thread.yield(); //让出了CPU，此时read线程执行则会获取到1
        number = 2;
    }

    public synchronized void read(){
        if (ready) {
            result = number * 3;
        }
        System.out.println("result的值为:" + result);
    }

    private class ReadWriteThread extends Thread {
        private boolean flag;

        public ReadWriteThread(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag) {
                write();
            }
            else{
                read();
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo demo = new SynchronizedDemo();
        //写线程
        demo.new ReadWriteThread(true).start();
        //读线程
        demo.new ReadWriteThread(false).start();
    }
}


