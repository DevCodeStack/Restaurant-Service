package com.eatza.restaurant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class RestaurantRequestDto {
	
	private String name;
	private String location;
	private String cuisine;
	private double rating;

}
