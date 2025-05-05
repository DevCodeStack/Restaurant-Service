package com.eatza.restaurant.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "course", schema = "eatza")
public class Course {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@OneToOne
	@JoinColumn(name = "cuisine_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Cuisine cuisine;
	
	@OneToOne
	@ColumnDefault("0")
	@JoinColumn(name = "active_id", nullable = false)
	@OnDelete(action = OnDeleteAction.SET_DEFAULT)
	private ActiveHours activeHours;

}
