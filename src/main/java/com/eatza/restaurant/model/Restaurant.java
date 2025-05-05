package com.eatza.restaurant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurants", schema = "eatza")
@Getter @Setter @NoArgsConstructor
public class Restaurant {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String location;
	private double rating;

	public Restaurant(String name, String location, double rating) {
		super();
		this.name = name;
		this.location = location;
		this.rating = rating;
	}
	
}
