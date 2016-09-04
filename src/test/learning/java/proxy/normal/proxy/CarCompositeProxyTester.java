package learning.java.proxy.normal.proxy;

import learning.java.proxy.normal.impl.Car;
import org.junit.Test;

/**
 * CarCompositeProxyTester
 *
 * @author hwm
 * @since 2016/9/5
 **/
public class CarCompositeProxyTester {


    @Test
    public void testMove(){
        Car car = new Car();
        CarCompositeProxy proxy = new CarCompositeProxy(car);
        proxy.move();

    }
}
