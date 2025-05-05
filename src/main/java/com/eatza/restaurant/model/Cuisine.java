package com.eatza.restaurant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Builder @AllArgsConstructor
@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "cuisine", schema = "eatza", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Cuisine {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	public Cuisine(String name) {
		super();
		this.name = name;
	}
}
