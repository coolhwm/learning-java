package learning.java.concurrent.base;

public class KeyPersonThread extends  Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始战斗！");
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "发起攻击...");
        }

        System.out.println(Thread.currentThread().getName() + "结束战斗！");
    }
}
