package learning.java.junit;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {


    private static ApplicationContext context;

    @BeforeClass
    public static void setUpBeforeClass(){
        context = new ClassPathXmlApplicationContext("learning/applicationContext.xml");
    }

    @Test
    public void testSpring(){
        Object now = context.getBean("now");
        System.out.println(now);
    }


}
