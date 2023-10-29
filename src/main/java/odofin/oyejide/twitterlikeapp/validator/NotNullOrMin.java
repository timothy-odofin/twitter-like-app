package odofin.oyejide.twitterlikeapp.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotNull
@Min(1)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface NotNullOrMin {
    String message() default "The field must not be null and must be greater than or equal to 1";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
