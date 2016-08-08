package learning.java.concurrent.communication;


public class EnergySystem {

    private final double[] energyBoxes;

    /**
     * 锁对象
     */
    private final Object lockObject = new Object();

    /**
     * 构造函数
     * @param n 能量盒子的总数
     * @param initialEnergy 每个能量盒子的初始含有的能量值
     */
    public EnergySystem(int n, double initialEnergy){
        energyBoxes = new double[n];
        for(int i = 0; i< energyBoxes.length; i++){
            energyBoxes[i] = initialEnergy;
        }
    }

    /**
     * 能量转移
     * @param form 来源
     * @param to 终点
     * @param amount 数量
     */
    public void transfer(int form, int to, double amount){
        //互斥操作
        synchronized (lockObject){

            while(energyBoxes[form] < amount){
                try {
                    lockObject.wait();
                    System.out.println(Thread.currentThread().getName() + "-->能量不足，等待");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName());
            energyBoxes[form] -= amount;
            //System.out.printf("从%d转移%10.2f单位能量到%d",form,amount,to);
            energyBoxes[to] += amount;
            //System.out.printf("能量总和：%10.2f%n", getTotalEnergies());

            lockObject.notifyAll();
        }

    }

    /**
     * 获取总能量
     */
    public double getTotalEnergies(){
        double sum = 0;
        for (double energyBox : energyBoxes) {
            sum += energyBox;
        }
        return sum;
    }

    /**
     * 获取能量盒子的长度
     */
    public int getBoxAmount(){
        return energyBoxes.length;
    }

}
