package learning.java.proxy.normal.proxy;

import learning.java.proxy.normal.Moveable;
import org.junit.Test;

/**
 * CarInheritedProxyTester
 *
 * @author hwm
 * @since 2016/9/5
 **/
public class CarInheritedProxyTester {

    @Test
    public void testMove(){
        Moveable car = new CarInheritedProxy();
        car.move();

    }
}
