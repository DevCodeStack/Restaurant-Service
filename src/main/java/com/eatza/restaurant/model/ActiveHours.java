package com.eatza.restaurant.model;

import java.time.LocalTime;

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
@Table(name = "active_hours", schema = "eatza")
public class ActiveHours {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalTime activeFrom;
	private LocalTime activeTill;
	
	public ActiveHours(LocalTime activeFrom, LocalTime activeTill) {
		this.activeFrom = activeFrom;
		this.activeTill = activeTill;
	}

}
