package learning.java.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * TimeHandler
 *
 * @author hwm
 * @since 2016/9/5
 **/
public class TimeHandler implements InvocationHandler {

    //被代理的对象
    private Object target;

    //使用被代理的对象构造
    public TimeHandler(Object traget) {
        this.target = traget;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("汽车开始行使.....");

        //调用被代理的方法
        method.invoke(target);

        long end = System.currentTimeMillis();
        System.out.println("汽车行驶结束.... 行驶时间为：" + (end - start) + "ms");
        return null;
    }
}
