package learning.java.proxy.normal.proxy;

import learning.java.proxy.normal.Moveable;

/**
 * CarTimeProxt
 *
 * @author hwm
 * @since 2016/9/5
 **/
public class CarTimeProxy extends CarCompositeProxy {

    public CarTimeProxy(Moveable moveable) {
        super(moveable);
    }

}
