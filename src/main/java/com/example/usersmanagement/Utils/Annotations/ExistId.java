package com.example.usersmanagement.Utils.Annotations;

import com.example.usersmanagement.Utils.Validators.ExistIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {ExistIdValidator.class})
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistId {
    String message() default "id does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
