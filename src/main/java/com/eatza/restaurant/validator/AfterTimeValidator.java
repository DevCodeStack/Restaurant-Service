package com.eatza.restaurant.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.eatza.restaurant.util.AfterTime;

public class AfterTimeValidator implements ConstraintValidator<AfterTime, LocalTime> {

    private LocalTime referenceTime;

    @Override
    public void initialize(AfterTime constraintAnnotation) {
        this.referenceTime = LocalTime.parse(constraintAnnotation.time(), DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Use @NotNull for null checks
        }
        return value.isAfter(referenceTime);
    }
}