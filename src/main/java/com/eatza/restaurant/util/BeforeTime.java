package com.eatza.restaurant.util;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Payload;

@Retention(RUNTIME)
@Target(FIELD)
public @interface BeforeTime {
	
	String message() default "Time must be before {time}";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	String time();
}
