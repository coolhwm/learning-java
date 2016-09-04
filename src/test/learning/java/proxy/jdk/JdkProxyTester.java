package learning.java.proxy.jdk;

import learning.java.proxy.jdk.handler.TimeHandler;
import learning.java.proxy.normal.Moveable;
import learning.java.proxy.normal.impl.Car;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 *
 * @author hwm
 * @since 2016/9/5
 **/
public class JdkProxyTester {

    @Test
    public void testTimeHandler() {
        Car car = new Car();
        InvocationHandler h = new TimeHandler(car);

        Moveable m = (Moveable)Proxy.newProxyInstance(car.getClass().getClassLoader(), new Class[]{Moveable.class}, h);

        m.move();
    }
}
