package learning.java.proxy.normal.proxy;

import learning.java.proxy.normal.impl.Car;

/**
 * 继承代理
 *
 * @author hwm
 * @since 2016/9/5
 **/
public class CarInheritedProxy extends Car {

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        System.out.println("汽车开始行使.....");

        //继承静态代理，调用被代理的方法
        super.move();

        long end = System.currentTimeMillis();
        System.out.println("汽车行驶结束.... 行驶时间为：" + (end - start) + "ms");
    }
}
