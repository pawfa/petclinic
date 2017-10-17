package hello.security.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidationConstraintValidator implements ConstraintValidator<EmailValidation,String> {


    @Override
    public void initialize(EmailValidation emailValidation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !s.contains("@petclinic.com");
    }
}
