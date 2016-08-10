package learning.java.junit;


import org.junit.*;

public class JunitFlowTest {

    @AfterClass
    public static void tearDownAfterClass(){
        System.out.println("tearDownAfterClass...");
    }

    @BeforeClass
    public static void setUpBeforeClass(){
        System.out.println("setUpBeforeClass...");
    }

    @Before
    public void setUp(){
        System.out.println("setUp...");
    }

    @After
    public void tearDown(){
        System.out.println("tearDown...");
    }

    @Test
    public void test1(){
        System.out.println("testing1..");
    }

    @Test
    public void test2(){
        System.out.println("testing2..");
    }
}
