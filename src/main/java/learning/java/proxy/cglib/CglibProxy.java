package learning.java.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CglibProxy
 *
 * @author hwm
 * @since 2016/9/5
 **/
public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz){
        //创建类的子类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //创建对象
        return enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("日志开始...");
        //调用代理类父类的方法
        methodProxy.invokeSuper(o, args);
        System.out.println("日志结束...");
        return null;
    }
}
