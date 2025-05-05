package com.eatza.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eatza.restaurant.model.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long>{
	
	@Query(nativeQuery = true, value = """
			select mi.* from eatza.menu_items mi
			where mi.course_id in (
			select c.id from eatza.course c
			join eatza.menu m on m.cuisine_id = c.cuisine_id
			where m.restaurant_id = ?1)
			""")
	public List<MenuItem> findByRestaurantId(Long restaurantId);
	
}
