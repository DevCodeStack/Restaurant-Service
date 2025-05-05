package com.eatza.restaurant.validator;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.eatza.restaurant.util.BeforeTime;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BeforeTimeValidator implements ConstraintValidator<BeforeTime, LocalTime> {

    private LocalTime referenceTime;

    @Override
    public void initialize(BeforeTime constraintAnnotation) {
        this.referenceTime = LocalTime.parse(constraintAnnotation.time(), DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Use @NotNull for null checks
        }
        return value.isBefore(referenceTime);
    }
}