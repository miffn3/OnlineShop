package net.thumbtack.onlineshop.validation.annotation;

import net.thumbtack.onlineshop.validation.validator.DuplicateLoginConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= DuplicateLoginConstraintValidator.class)
public @interface DuplicateLogin {
    String message() default "User with this login is already exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
