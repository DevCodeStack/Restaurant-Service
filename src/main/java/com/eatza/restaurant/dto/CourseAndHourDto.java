package com.eatza.restaurant.dto;

import java.time.LocalTime;

import com.eatza.restaurant.util.AfterTime;
import com.eatza.restaurant.util.BeforeTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseAndHourDto {
	
	@NotNull
	@Min(value = 1)
	private Long cuisineId;
	
	@NotEmpty(message = "The full name of course is required.")
	@Size(min = 2, max = 100, message = "The length of full name must be between 2 and 100 characters.")
	private String name;
	
	@NotNull(message = "ActiveFrom cannot be null")
	@AfterTime(time = "08:00", message = "Opening time must be after 08:00")
	private LocalTime activeFrom;
	
	@NotNull(message = "ActiveTill cannot be null")
	@BeforeTime(time = "22:00", message = "Closing time must not exceed 22:00")
	private LocalTime activeTill;

}
