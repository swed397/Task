package com.example.demo.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MovieTypeValidator.class)
public @interface ValidateMovieType {

    String message() default "Invalid movie type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
