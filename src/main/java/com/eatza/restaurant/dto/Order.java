package com.eatza.restaurant.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Order {
	
	private Long id;
	private Long customerId;
	private String status;
	private Long restaurantId;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

}
