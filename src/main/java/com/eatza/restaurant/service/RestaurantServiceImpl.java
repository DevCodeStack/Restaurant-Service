package com.eatza.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eatza.restaurant.exception.RestaurantException;
import com.eatza.restaurant.model.MenuItem;
import com.eatza.restaurant.model.Restaurant;
import com.eatza.restaurant.repository.MenuItemRepository;
import com.eatza.restaurant.repository.RestaurantRepository;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	MenuItemRepository menuItemRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;

	@Override
	public List<MenuItem> getItemsByRestaurantId(Long restaurantId, Integer pageNumber, Integer pageSize) throws RestaurantException {
		try {
			
			return menuItemRepository.findByRestaurantId(restaurantId);
			
		} catch(Exception ex) {
			throw new RestaurantException(ex.getMessage());
		}
	}

	@Override
	public List<Restaurant> getAllRestaurants(Integer pageNumber, Integer pageSize) throws RestaurantException {
		try {
			
			return restaurantRepository.findAll();
			
		} catch(Exception ex) {
			throw new RestaurantException(ex.getMessage());
		}
	}

	@Override
	public Restaurant getRestaurantById(Long restaurantId) throws RestaurantException {
		try {
			
			Optional<Restaurant> restaurant= restaurantRepository.findById(restaurantId);
			return restaurant.isPresent()?restaurant.get():null;
			
		} catch(Exception ex) {
			throw new RestaurantException(ex.getMessage());
		}
	}
	
	@Override
	public List<Restaurant> getRestaurantsByBudget(Integer totalBudget, Integer pageNumber, Integer pageSize) throws RestaurantException {
		try {
			
			return restaurantRepository.findByBudget(totalBudget);
			
		} catch(Exception ex) {
			throw new RestaurantException(ex.getMessage());
		}
	}

	@Override
	public List<Restaurant> getRestaurantsByName(String restaurantName, Integer pageNumber, Integer pageSize) throws RestaurantException {
		try{
			
			return restaurantRepository.findByName(restaurantName);
			
		} catch(Exception ex) {
			throw new RestaurantException(ex.getMessage());
		}
	}

	@Override
	public List<Restaurant> getRestaurantsByRating(Double rating, Integer pageNumber, Integer pageSize) throws RestaurantException {
		try{
			
			return restaurantRepository.findByRating(rating);
			
		} catch(Exception ex) {
			throw new RestaurantException(ex.getMessage());
		}
	}

	@Override
	public List<Restaurant> getRestaurantsByLocationAndCuisine(String location, String cuisine, Integer pageNumber,
			Integer pageSize) throws RestaurantException {
		try{
			
			return restaurantRepository.findByLocationAndCuisine(location, cuisine);
			
		} catch(Exception ex) {
			throw new RestaurantException(ex.getMessage());
		}
	}

	@Override
	public List<Restaurant> getRestaurantsByNameAndLocation(String restaurantName, String location, Integer pageNumber,
			Integer pageSize) throws RestaurantException {
		try{
			
			return restaurantRepository.findByNameAndLocation(restaurantName, location);
			
		} catch(Exception ex) {
			throw new RestaurantException(ex.getMessage());
		}
	}

	@Override
	public MenuItem getItemByItemId(Long itemId) throws RestaurantException {
		try{
			
			Optional<MenuItem> item = menuItemRepository.findById(itemId);
			return item.isPresent()?item.get():null;
			
		} catch(Exception ex) {
			throw new RestaurantException(ex.getMessage());
		}
	}

	@Override
	public Integer updateRestaurantRating(Long restaurantId, Double rating) throws RestaurantException {
		try{
			
			return restaurantRepository.updateRating(restaurantId, rating);
			
		} catch(Exception ex) {
			throw new RestaurantException(ex.getMessage());
		}
	}


}
