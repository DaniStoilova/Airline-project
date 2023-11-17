package com.example.airline.validation.annotations;

import com.example.airline.validation.validators.UniquePhoneNumberValidators;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniquePhoneNumberValidators.class)
public @interface UniquePhoneNumber {

    String message() default  "Phone number already exist";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
