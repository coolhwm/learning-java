package learning.java.concurrent.base;

/**
 * 军队线程，模拟算法的行为
 */
public class ArmyRunnable implements Runnable {

    //volatile 保证了线程个可以正确读取其他线程写入的值
    //可见性  ref JMM
    volatile boolean keepRunning = true;

    @Override
    public void run() {
        while(keepRunning){
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "进攻对方["+ i +"]");
                //让出处理器时间，下次还需要竞争CPU
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread().getName() + "结束了战斗！");
    }
}
