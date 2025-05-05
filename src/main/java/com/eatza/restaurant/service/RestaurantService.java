package com.eatza.restaurant.service;

import java.util.List;

import com.eatza.restaurant.exception.RestaurantException;
import com.eatza.restaurant.model.MenuItem;
import com.eatza.restaurant.model.Restaurant;

public interface RestaurantService {

	List<MenuItem> getItemsByRestaurantId(Long restaurantId, Integer pageNumber, Integer pageSize) throws RestaurantException;

	List<Restaurant> getAllRestaurants(Integer pageNumber, Integer pageSize) throws RestaurantException;
	
	List<Restaurant> getRestaurantsByName(String restaurantName, Integer pageNumber, Integer pageSize) throws RestaurantException;

	List<Restaurant> getRestaurantsByRating(Double rating, Integer pageNumber, Integer pageSize) throws RestaurantException;

	List<Restaurant> getRestaurantsByLocationAndCuisine(String location, String cuisine, Integer pageNumber, Integer pageSize) throws RestaurantException;

	List<Restaurant> getRestaurantsByNameAndLocation(String restaurantName, String location, Integer pageNumber, Integer pageSize) throws RestaurantException;

	MenuItem getItemByItemId(Long itemId) throws RestaurantException;

	Restaurant getRestaurantById(Long restaurantId) throws RestaurantException;

	Integer updateRestaurantRating(Long restaurantId, Double rating) throws RestaurantException;

}
