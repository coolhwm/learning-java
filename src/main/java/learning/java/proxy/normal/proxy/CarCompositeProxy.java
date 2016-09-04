package learning.java.proxy.normal.proxy;

import learning.java.proxy.normal.Moveable;

/**
 * CarCompositeProxy
 *
 * @author hwm
 * @since 2016/9/5
 **/
public class CarCompositeProxy  implements Moveable{

    /**
     * 被代理的对象
     */
    private Moveable moveable;

    /**
     * 构造函数，使用被代理对象构造
     */
    public CarCompositeProxy(Moveable moveable) {
        this.moveable = moveable;
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        System.out.println("汽车开始行使.....");

        //聚合静态代理，调用被代理的方法
        moveable.move();

        long end = System.currentTimeMillis();
        System.out.println("汽车行驶结束.... 行驶时间为：" + (end - start) + "ms");
    }
}
