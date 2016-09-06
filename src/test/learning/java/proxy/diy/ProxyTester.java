package learning.java.proxy.diy;

import learning.java.proxy.normal.Moveable;
import learning.java.proxy.normal.impl.Car;
import org.junit.Test;

/**
 * ProxyTester
 *
 * @author hwm
 * @since 2016/9/5
 **/
public class ProxyTester {

    @Test
    public void testProxy() throws Exception {
        Car car = new Car();
        TimeHandler h = new TimeHandler(car);
        Moveable m = (Moveable)Proxy.newProxyInstance(Moveable.class, h);
        m.move();
    }
}
