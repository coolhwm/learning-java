package learning.java.junit;


import org.junit.Assert;
import org.junit.Test;

public class FailureAndErrorTester {

    @Test
    public void testFailure(){
        Assert.assertEquals(1,2);
    }

    @Test
    public void testError(){
        int a = 1/0;
    }

}
