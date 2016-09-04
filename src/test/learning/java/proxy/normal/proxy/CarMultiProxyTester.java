package learning.java.proxy.normal.proxy;

import learning.java.proxy.normal.Moveable;
import learning.java.proxy.normal.impl.Car;
import org.junit.Test;

/**
 * CarProxyTester
 *
 * @author hwm
 * @since 2016/9/5
 **/
public class CarMultiProxyTester {

    @Test
    public void testMultiProxy1(){
        Moveable car = new Car();
        Moveable logProxy = new CarLogProxy(car);
        Moveable timeProxy = new CarTimeProxy(logProxy);

        timeProxy.move();
    }

    @Test
    public void testMultiProxy2(){
        Moveable car = new Car();
        Moveable timeProxy = new CarTimeProxy(car);
        Moveable logProxy = new CarLogProxy(timeProxy);
        logProxy.move();
    }
}
