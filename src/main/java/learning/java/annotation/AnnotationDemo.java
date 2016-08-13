package learning.java.annotation;

public class AnnotationDemo {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Person person = new Person();
        person.sing();
    }
}
