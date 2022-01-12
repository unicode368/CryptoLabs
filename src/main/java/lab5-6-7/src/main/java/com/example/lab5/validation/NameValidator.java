package com.example.lab5.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class NameValidator
        implements ConstraintValidator<ValidName, String> {

    String NAME_REGEX_UA = "^[А-ЩЮЯҐІЇЄ][а-щьюяґіїє']{1,20}$";
    String NAME_REGEX = "^[A-Z][a-z]{1,20}$";

    @Override
    public void initialize(ValidName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name,
                           ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(NAME_REGEX, name);
    }

}
