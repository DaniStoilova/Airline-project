package com.example.airline.validation.validators;

import com.example.airline.service.UserService;
import com.example.airline.validation.annotations.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;


public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {

    private String message;

    private final UserService userService;

    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {

            return true;

        } else {

             boolean isUniqueEmail = userService.existsByEmail(value);

            if (!isUniqueEmail) replaceMessage(context, this.message);

            return isUniqueEmail;
        }
    }

    private void replaceMessage(ConstraintValidatorContext context, String message) {
        context
                .unwrap(HibernateConstraintValidatorContext.class)
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }


}
