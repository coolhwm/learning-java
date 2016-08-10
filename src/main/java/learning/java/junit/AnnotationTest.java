package learning.java.junit;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class AnnotationTest {

    @Test(expected = ArithmeticException.class)
    public void testException(){
        int i = 1/0;
    }

    @Ignore("it ok ...")
    @Test(timeout = 1000)
    public void testTimeout(){
        while(true){}
    }
}
