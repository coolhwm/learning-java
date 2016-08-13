package learning.java.concurrent.ticket;

public class TicketsDemo {
    public static void main(String[] args) {
        TicketsRunnable r = new TicketsRunnable();
        for (int i = 0; i < 3; i++) {
            //TicketsThread t = new TicketsThread("窗口：" + i)
            Thread thread = new Thread(r, "窗口" + i);
            thread.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



class TicketsThread extends Thread{

    //票的总量
    private int ticketsCount = 5;
    //线程名称
    private String name;

    public TicketsThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (ticketsCount > 0) {
            ticketsCount--;
            System.out.println(name + "买了1张票，还剩：" + ticketsCount);
        }
    }
}

class TicketsRunnable implements  Runnable {

    //票的总量
    private int ticketsCount = 5;

    @Override
    public void run() {
        synchronized (this) {
            while (ticketsCount > 0) {
                ticketsCount--;
                Thread.yield();
                System.out.println(Thread.currentThread().getName() + "卖了1张票，还剩：" + ticketsCount);
                Thread.yield();
            }
        }
    }
}
