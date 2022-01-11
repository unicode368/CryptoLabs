package com.example.lab5.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class LoginValidator
        implements ConstraintValidator<ValidLogin, String> {

    String LOGIN_REGEX = "^[\\p{L}0-9_.-]*\\p{L}[\\p{L}0-9_.-]{2,20}$";

    @Override
    public void initialize(ValidLogin constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email,
                           ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(LOGIN_REGEX, email);
    }
}
