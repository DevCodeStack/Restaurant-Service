package com.eatza.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eatza.restaurant.model.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long>{
	
	@Query(nativeQuery = true, value = "select * from eatza.menu_items where menu_Id in (select id from eatza.menu where restaurant_Id = ?1)")
	public List<MenuItem> findByRestaurantId(Long restaurantId);
	
}
