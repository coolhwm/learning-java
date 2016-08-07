package learning.java.concurrent.base;

/**
 * 舞台线程
 */
public class Stage extends Thread {
    @Override
    public void run() {
        System.out.println("隋唐演义开始");

        sleepThread(1000);

        System.out.println("大幕徐徐拉开。。。");

        sleepThread(1000);

        System.out.println("隋朝末年....");

        //军队
        ArmyRunnable sui = new ArmyRunnable();
        ArmyRunnable revolt = new ArmyRunnable();

        //创建线程
        Thread suiThread = new Thread(sui, "隋军");
        Thread revoltThread = new Thread(revolt, "农民起义军");

        //开始作战
        suiThread.start();
        revoltThread.start();

        //舞台线程休眠
        sleepThread(50);

        System.out.println("正当双方交战正酣，半路杀出了个程咬金!!!");

        Thread mrCheng = new KeyPersonThread();
        mrCheng.setName("程咬金");

        System.out.println("程咬金出场！！");

        //停止线程
        sui.keepRunning = false;
        revolt.keepRunning = false;

        //舞台休眠
        sleepThread(2000);

        //开始关键人物线程
        mrCheng.start();

        //让其他线程给等待
        try {
            mrCheng.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("战争结束，人名安居乐业");

    }


    private void sleepThread(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Stage().start();
    }
}
