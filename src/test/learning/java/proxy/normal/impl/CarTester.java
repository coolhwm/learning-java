package learning.java.proxy.normal.impl;

import learning.java.proxy.normal.Moveable;
import org.junit.Test;

/**
 * CarTester
 *
 * @author hwm
 * @since 2016/9/4
 **/
public class CarTester {

    @Test
    public void testMove(){
        Moveable car = new Car();
        car.move();;
    }
}
