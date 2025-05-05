package com.eatza.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eatza.restaurant.model.Cuisine;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Long>{
	
	Optional<List<Cuisine>> findByName(String name);
	
}
