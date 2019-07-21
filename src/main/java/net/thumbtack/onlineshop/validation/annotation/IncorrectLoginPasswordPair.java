package net.thumbtack.onlineshop.validation.annotation;

import net.thumbtack.onlineshop.validation.validator.IncorrectLoginPasswordConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= IncorrectLoginPasswordConstraintValidator.class)
public @interface IncorrectLoginPasswordPair {
    String message() default "User with this login and password pair does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
