package odofin.oyejide.twitterlikeapp.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RoleValidator.class)
@Documented
public @interface ValidRole {

    String message() default "Invalid role. Allowed values are {Publisher, Subscriber}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
