package learning.java.proxy.normal.impl;

import learning.java.proxy.normal.Moveable;

import java.util.Random;

/**
 * Car
 *
 * @author hwm
 * @since 2016/9/4
 **/
public class Car implements Moveable {

    @Override
    public void move() {

        try {
            Thread.sleep(new Random().nextInt(1000));
            System.out.println("汽车行驶中.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
