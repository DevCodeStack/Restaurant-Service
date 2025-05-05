package com.eatza.restaurant.util;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.eatza.restaurant.validator.AfterTimeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = AfterTimeValidator.class)
public @interface AfterTime {
	
	String message() default "Time must be after {time}";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	String time();
}
