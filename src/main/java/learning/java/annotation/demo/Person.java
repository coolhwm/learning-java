package learning.java.annotation.demo;


@Description(desc = "annotation class")
public class Person {

    @Description(desc = "annotation field")
    private String name;

    @Deprecated
    @Description(desc = "annotation method")
    public void sing(){}
}
