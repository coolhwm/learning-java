package learning.java.proxy.diy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * TimeHander
 *
 * @author hwm
 * @since 2016/9/5
 **/
public class TimeHandler implements InvocationHandler{

    private Object target;

    public TimeHandler(Object target) {
        this.target = target;
    }

    @Override
    public void invoke(Object o, Method method) {
        try {
            long start = System.currentTimeMillis();
            System.out.println("计时开始.....");

            method.invoke(target);

            long end = System.currentTimeMillis();
            System.out.println("执行耗时为：" + (end - start) + "ms");
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
