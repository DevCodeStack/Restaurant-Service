package com.eatza.restaurant.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Builder @AllArgsConstructor
@Getter @Setter @NoArgsConstructor
public class CuisineRequestDto {
	
	@NotEmpty(message = "Cuisine names can't have null or empty values")
	private List<String> names;
	
}
