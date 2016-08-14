package learning.java.annotation.demo;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({TYPE, METHOD, FIELD})
@Retention(RUNTIME)
@Inherited
@Documented
public @interface Description {
    String desc();
    String author() default  "";
    int age() default 0;
}
