package com.eatza.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.eatza.restaurant.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
	@Query(nativeQuery = true, value = "select * from eatza.restaurants where name = ?1")
	public List<Restaurant> findByName(String name);
	
	@Query(nativeQuery = true, value = "select * from eatza.restaurants where rating = ?1")
	public List<Restaurant> findByRating(Double rating);
	
	@Query(nativeQuery = true, value = """
			select r.* from restaurant r
			join menu m on m.restaurant_id = r.id
			join cuisine c on c.id = m.cuisine_id
			where r.location = ?1 and c.name = ?2
			""")
	public List<Restaurant> findByLocationAndCuisine(String location, String cuisine);
	
	@Query(nativeQuery = true, value = "select * from eatza.restaurants where name = ?1 and location = ?2")
	public List<Restaurant> findByNameAndLocation(String name, String location);

	@Modifying
	@Query(nativeQuery = true, value = "update eatza.restaurants set rating = ?2 where id = ?1")
	public Integer updateRating(Long restaurantId, Double rating);
	
}
