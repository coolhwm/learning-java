package learning.java.proxy.cglib;

import org.junit.Test;

/**
 * CglibProxyTester
 *
 * @author hwm
 * @since 2016/9/5
 **/
public class CglibProxyTester {


    @Test
    public void testProxy(){
        CglibProxy cglibProxy = new CglibProxy();
        Train t = (Train) cglibProxy.getProxy(Train.class);
        t.move();
    }
}
