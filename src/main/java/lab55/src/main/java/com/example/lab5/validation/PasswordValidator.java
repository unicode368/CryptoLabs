package com.example.lab5.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator
        implements ConstraintValidator<ValidPassword, String> {

    String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }
}
