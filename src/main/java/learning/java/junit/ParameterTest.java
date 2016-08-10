package learning.java.junit;

import learning.java.junit.bean.Calculate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParameterTest {

    int expected = 0;
    int input1 = 0;
    int input2 = 0;

    @Test
    public void testAdd(){
        Assert.assertEquals(expected, new Calculate().add(input1,input2));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> t(){
        return Arrays.asList(new Object[][]{
                {3,1,2},
                {4,2,2}
        });
    }

    public ParameterTest(int expected, int input1, int input2) {
        this.expected = expected;
        this.input1 = input1;
        this.input2 = input2;
    }
}
