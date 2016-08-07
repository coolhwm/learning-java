package learning.java.concurrent.demo;


public class Actor  extends Thread{

    @Override
    public void run() {
        System.out.println(getName() +  "是一个演员");
        int count = 0;
        boolean keepRunning = true;
        while(keepRunning){
            System.out.println(getName() + "登台演出:" + (++count));
            if(count == 100){
                keepRunning = false;
            }
            if(count % 10 == 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(getName() +  "的演出结束了");

    }

    public static void main(String[] args) {
        Actor actor = new Actor();
        actor.setName("Mr. Thread");
        actor.start();

        Thread actress= new Thread(new Actress(), "Ms. Runnable");
        actress.start();

    }
}
