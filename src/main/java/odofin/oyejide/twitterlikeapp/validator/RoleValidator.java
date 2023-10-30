package odofin.oyejide.twitterlikeapp.validator;

import odofin.oyejide.twitterlikeapp.utils.AppUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class RoleValidator implements ConstraintValidator<ValidRole, String> {


    @Override
    public void initialize(ValidRole constraintAnnotation) {
        // Not needed for this example
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && AppUtils.USER_ROLES.contains(value.toLowerCase());
    }
}
