package com.example.lab5.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = lab55.src.main.java.com.example.lab5.validation.EmailValidator.class)
@Documented
public @interface ValidEmail {
    String message() default "email.invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
