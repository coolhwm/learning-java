package learning.java.proxy.diy;

import java.lang.reflect.Method;

/**
 * 动态代理事务处理器
 *
 * @author hwm
 * @since 2016/9/5
 **/
public interface InvocationHandler {
    void invoke(Object o, Method method);
}
