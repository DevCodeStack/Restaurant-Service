package com.eatza.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Builder @AllArgsConstructor
@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "menu", schema = "eatza")
public class Menu {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "restaurant_id")
	private Long restaurantId;
	@Column(name = "cuisine_id")
	private Long cuisineId;
	
	public Menu(Long restaurantId, Long cuisineId) {
		this.restaurantId = restaurantId;
		this.cuisineId = cuisineId;
	}
}
