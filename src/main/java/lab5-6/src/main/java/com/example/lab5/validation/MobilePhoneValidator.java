package com.example.lab5.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class MobilePhoneValidator
        implements ConstraintValidator<ValidMobilePhone, String> {

    String MOBILE_PHONE_REGEX = "^[0-9]{10}$";

    @Override
    public void initialize(ValidMobilePhone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email,
                           ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(MOBILE_PHONE_REGEX, email);
    }
}
