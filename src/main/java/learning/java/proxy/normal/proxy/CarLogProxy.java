package learning.java.proxy.normal.proxy;

import learning.java.proxy.normal.Moveable;

/**
 * CarLogProxy
 *
 * @author hwm
 * @since 2016/9/5
 **/
public class CarLogProxy implements Moveable{
    /**
     * 被代理的对象
     */
    private Moveable moveable;

    /**
     * 构造函数，使用被代理对象构造
     */
    public CarLogProxy(Moveable moveable) {
        this.moveable = moveable;
    }

    @Override
    public void move() {
        System.out.println("日志开始.....");

        //聚合静态代理，调用被代理的方法
        moveable.move();

        System.out.println("日志结束....");
    }
}
