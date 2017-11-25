package hello.security.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidationConstraintValidator implements ConstraintValidator<DateValidation,String> {


    @Override
    public void initialize(DateValidation dateValidation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || !s.matches("\\d{2}-\\d{2}-\\d{4}")){
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);

        try {
            //if not valid, it will throw ParseException
            Date date = sdf.parse(s);
        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }


        return true;
    }
}
