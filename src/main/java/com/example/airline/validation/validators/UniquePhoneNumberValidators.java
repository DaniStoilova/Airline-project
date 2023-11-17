package com.example.airline.validation.validators;

import com.example.airline.service.UserService;
import com.example.airline.validation.annotations.UniquePhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class UniquePhoneNumberValidators implements ConstraintValidator<UniquePhoneNumber,String> {

    private String message;

    private final UserService userService;

    public UniquePhoneNumberValidators(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void initialize(UniquePhoneNumber constraintAnnotation) {
        this.message=constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null){
            return true;
        }else{
            boolean isPhoneNumberExist = userService.existsByPhoneNumber(value);

            if (!isPhoneNumberExist) replaceMessage(context, this.message);

            return isPhoneNumberExist;
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
