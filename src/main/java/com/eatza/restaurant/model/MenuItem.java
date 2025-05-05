package com.eatza.restaurant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Builder @AllArgsConstructor
@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "menu_items", schema = "eatza")
public class MenuItem {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String diet;
//	private Long prepTime;
//	private Long cookTime;
	private Long timeToServe;
	private int servings;
	private int price;
	
	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Course course;

	public MenuItem(String name, String diet, Long timeToServe, int servings, int price, Course course) {
		super();
		this.name = name;
		this.diet = diet;
//		this.prepTime = prepTime;
//		this.cookTime = cookTime;
		this.timeToServe = timeToServe;
		this.servings = servings;
		this.price = price;
		this.course = course;
	}
	
}
