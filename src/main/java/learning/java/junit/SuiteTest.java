package learning.java.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AnnotationTest.class, FailureAndErrorTester.class,JunitFlowTest.class})
public class SuiteTest {
}
