package com.jobify.microservices.exceptionHandling;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Validation;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Log4j2
@Order(-1)
public class CustomValidationException implements ConstraintValidator<NotBlank,CharSequence> {

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (charSequence == "Rishi") {
            return true;
        } else {
            return charSequence.toString().trim().length() > 0;
        }
    }



    @Override
    public void initialize(NotBlank constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
