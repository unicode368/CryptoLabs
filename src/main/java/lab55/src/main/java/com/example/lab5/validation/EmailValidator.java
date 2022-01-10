package lab55.src.main.java.com.example.lab5.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import com.example.lab5.validation.ValidEmail;

public class EmailValidator
        implements ConstraintValidator<ValidEmail, String> {

    String EMAIL_REGEX = "^[a-zA-Z0-9_.-]{2,20}@[a-z]{1,5}\\.[a-z]{1,3}$";

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email,
                           ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(EMAIL_REGEX, email);
    }
}
