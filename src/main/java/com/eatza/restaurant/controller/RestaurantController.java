package com.eatza.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eatza.restaurant.exception.RestaurantException;
import com.eatza.restaurant.model.MenuItem;
import com.eatza.restaurant.model.Restaurant;
import com.eatza.restaurant.service.RestaurantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/restaurant")
@Slf4j
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;	
	
	@GetMapping
	@SecurityRequirement(name = "BearerAuth")
	@Operation(tags = "RestaurantController", description = "Get all restaurant")
	public ResponseEntity<List<Restaurant>> getAllRestaurants(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws RestaurantException {
		log.debug("In getAllRestaurants method");
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getAllRestaurants(pageNumber, pageSize));
	}
	
	@PostMapping
	@SecurityRequirement(name = "BearerAuth")
	@Operation(tags = "RestaurantController", description = "Add new restaurant")
	public ResponseEntity<List<Restaurant>> addRestaurants() throws RestaurantException {
		log.debug("In add Restaurants method");
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/{restaurantId}")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(tags = "RestaurantController", description = "Get restaurant by id")
	public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long restaurantId) throws RestaurantException {
		log.debug("In getAllRestaurants method");
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantById(restaurantId));
	}
	
	@GetMapping("/name/{restaurantName}")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(tags = "RestaurantController", description = "Get restaurants by name")
	public ResponseEntity<List<Restaurant>> getRestaurantsByName(@PathVariable String restaurantName,
			@RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws RestaurantException {
		log.debug("In getRestaurantsByName method");
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantsByName(restaurantName, pageNumber, pageSize));
		
	}
	
	@GetMapping("/rating/{rating}")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(tags = "RestaurantController", description = "Get restaurant by rating")
	public ResponseEntity<List<Restaurant>> getRestaurantsByRating(@PathVariable Double rating,
			@RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws RestaurantException {
		log.debug("In getRestaurantsByRating method");
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantsByRating(rating, pageNumber, pageSize));
		
	}
	
	@GetMapping("/location/{location}/cuisine/{cuisine}")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(tags = "RestaurantController", description = "Get restaurant by location and cuisine")
	public ResponseEntity<List<Restaurant>> getRestaurantsByLocationAndCuisine(@PathVariable String location, @PathVariable String cuisine,
			@RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws RestaurantException {
		log.debug("In getRestaurantsByLocationCuisine method");
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantsByLocationAndCuisine(location, cuisine, pageNumber, pageSize));
	}
	
	@GetMapping("/location/{location}/name/{restaurantName}")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(tags = "RestaurantController", description = "Get restaurant by location and name")
	public ResponseEntity<List<Restaurant>> getRestaurantsByLocationAndName(@PathVariable String restaurantName, @PathVariable String location,
			@RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws RestaurantException {
		log.debug("In getRestaurantsByLocationName method");
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantsByNameAndLocation(restaurantName, location, pageNumber, pageSize));
	}
	
	@PutMapping("/{restaurantId}/review")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(tags = "RestaurantController", description = "Update rating for restaurant")
	public ResponseEntity<Integer> updateRestaurantRating(@PathVariable Long restaurantId, @RequestParam Double rating) throws RestaurantException {
		log.debug("In updateRestaurantRating method");
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.updateRestaurantRating(restaurantId, rating));
	}
	
	@GetMapping("{restaurantId}/items")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(tags = "RestaurantController", description = "Get items by restaurant id")
	public ResponseEntity<List<MenuItem>> getItemsByRestaurantId(@PathVariable Long restaurantId,
			@RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws RestaurantException {
		log.debug("In getItemsByRestaurantId method");
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getItemsByRestaurantId(restaurantId, pageNumber, pageSize));		
	}
	
	@GetMapping("/item/{itemId}")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(tags = "RestaurantController", description = "Get item by item id")
	public ResponseEntity<MenuItem> getItemByItemId(@PathVariable Long itemId) throws RestaurantException {
		log.debug("In getItemByItemId method");
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getItemByItemId(itemId));		
	}
	
	
}
