package com.example.lab5.validation;

import com.ua.faculty.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
                implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object userDTO,
                           ConstraintValidatorContext constraintValidatorContext) {
        UserDTO user = (UserDTO) userDTO;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
