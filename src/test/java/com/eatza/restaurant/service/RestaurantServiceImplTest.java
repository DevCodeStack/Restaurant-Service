//package com.eatza.restaurant.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.eatza.restaurant.exception.RestaurantException;
//import com.eatza.restaurant.model.Menu;
//import com.eatza.restaurant.model.MenuItem;
//import com.eatza.restaurant.model.Restaurant;
//import com.eatza.restaurant.repository.MenuItemRepository;
//import com.eatza.restaurant.repository.RestaurantRepository;
//
//@ExtendWith(MockitoExtension.class)
//class RestaurantServiceImplTest {
//
//	@Mock
//	MenuItemRepository menuItemRepository;
//	
//	@Mock
//	RestaurantRepository restaurantRepository;
//	
//	@InjectMocks
//	RestaurantServiceImpl serviceImpl;
//	
//	private List<MenuItem> menuItems;
//	
//	private List<Restaurant> restaurants;
//	
//	@BeforeEach
//	void setUp() throws Exception {
//		Restaurant restaurant = new Restaurant();
//		restaurant.setId(1l);
//		restaurant.setName("Hotel Tridev");
//		restaurant.setLocation("Jatni");
//		restaurant.setCuisine("Indian veg");
//		restaurant.setBudget(1000);
//		restaurant.setRating(4.2);
//		
//		Restaurant newRestaurant = new Restaurant("Saffron spice", "Jatni", "Indian mixed", 2000, 4.2);
//		newRestaurant.setId(2l);
//		
//		Menu morningMenu = new Menu();
//		morningMenu.setId(1l);
//		morningMenu.setActiveFrom("7");
//		morningMenu.setActiveTill("10");
//		morningMenu.setRestaurant(restaurant);
//		
//		Menu afternoonMenu = new Menu("12", "14", restaurant);
//		afternoonMenu.setId(2l);
//		
//		MenuItem morningMenuItem1 = new MenuItem();
//		morningMenuItem1.setId(1l);
//		morningMenuItem1.setName("Dosa");
//		morningMenuItem1.setDescription("Onion Dosa");
//		morningMenuItem1.setPrice(30);
//		morningMenuItem1.setMenu(morningMenu);
//		
//		MenuItem morningMenuItem2 = new MenuItem();
//		morningMenuItem2.setId(2l);
//		morningMenuItem2.setName("Dosa");
//		morningMenuItem2.setDescription("Plain Dosa");
//		morningMenuItem2.setPrice(25);
//		morningMenuItem2.setMenu(morningMenu);
//		
//		MenuItem afternoonMenuItem1 = new MenuItem("Rice", "Plain Rice", 15, afternoonMenu);
//		afternoonMenuItem1.setId(3l);
//		
//		MenuItem afternoonMenuItem2 = new MenuItem("Rice", "Curd Rice", 35, afternoonMenu);
//		afternoonMenuItem2.setId(4l);
//		
//		menuItems = new ArrayList<>();
//		menuItems.add(morningMenuItem1);
//		menuItems.add(morningMenuItem2);
//		menuItems.add(afternoonMenuItem1);
//		menuItems.add(afternoonMenuItem2);
//		
//		restaurants = new ArrayList<>();
//		restaurants.add(restaurant);
//		restaurants.add(newRestaurant);
//		
//	}
//
//	@Test
//	void getItemsByRestaurantId_Success() {
//		Long restaurantId = 1l;
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(menuItemRepository.findByRestaurantId(restaurantId)).thenReturn(menuItems);
//		
//		List<MenuItem> returnedMenuItems = serviceImpl.getItemsByRestaurantId(restaurantId, pageNumber, pageSize);
//		assertThat(returnedMenuItems.equals(menuItems));
//		
//	}
//	
//	@Test
//	void getItemsByRestaurantId_Failed() {
//		Long restaurantId = 1l;
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(menuItemRepository.findByRestaurantId(restaurantId)).thenThrow(new RuntimeException());
//		
//		assertThrows(RestaurantException.class, () -> {serviceImpl.getItemsByRestaurantId(restaurantId, pageNumber, pageSize);});
//		
//	}
//	
//	@Test
//	void getAllRestaurants_Success() {
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantRepository.findAll()).thenReturn(restaurants);
//		
//		List<Restaurant> returnedRestaurants = serviceImpl.getAllRestaurants(pageNumber, pageSize);
//		assertThat(returnedRestaurants.equals(restaurants));
//		
//	}
//	
//	@Test
//	void getAllRestaurants_Failed() {
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantRepository.findAll()).thenThrow(new RuntimeException());
//		
//		assertThrows(RestaurantException.class, () -> {serviceImpl.getAllRestaurants(pageNumber, pageSize);});
//		
//	}
//	
//	@Test
//	void getRestaurantById_Success() {
//		Long restaurantId = 1l;
//		
//		when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurants.get(0)));
//		
//		Restaurant returnedRestaurant = serviceImpl.getRestaurantById(restaurantId);
//		assertThat(returnedRestaurant.equals(restaurants.get(0)));
//		
//	}
//	
//	@Test
//	void getRestaurantById_EmptyResult() {
//		Long restaurantId = 1l;
//		
//		when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());
//		
//		Restaurant returnedRestaurant = serviceImpl.getRestaurantById(restaurantId);
//		assertThat(returnedRestaurant == null);
//		
//	}
//	
//	@Test
//	void getRestaurantById_Failed() {
//		Long restaurantId = 1l;
//		
//		when(restaurantRepository.findById(restaurantId)).thenThrow(new RuntimeException());
//		
//		assertThrows(RestaurantException.class, () -> {serviceImpl.getRestaurantById(restaurantId);});
//		
//	}
//	
//	@Test
//	void getRestaurantsByBudget_Success() {
//		Integer totalBudget = 1000;
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantRepository.findByBudget(totalBudget)).thenReturn(restaurants);
//		
//		List<Restaurant> returnedRestaurants = serviceImpl.getRestaurantsByBudget(totalBudget, pageNumber, pageSize);
//		assertThat(returnedRestaurants.equals(restaurants));
//		
//	}
//	
//	@Test
//	void getRestaurantsByBudget_Failed() {
//		Integer totalBudget = 1000;
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantRepository.findByBudget(totalBudget)).thenThrow(new RuntimeException());
//		
//		assertThrows(RestaurantException.class, () -> {serviceImpl.getRestaurantsByBudget(totalBudget, pageNumber, pageSize);});
//		
//	}
//	
//	@Test
//	void getRestaurantsByName_Success() {
//		String name = "Saffron spice";
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantRepository.findByName(name)).thenReturn(restaurants);
//		
//		List<Restaurant> returnedRestaurants = serviceImpl.getRestaurantsByName(name, pageNumber, pageSize);
//		assertThat(returnedRestaurants.equals(restaurants));
//		
//	}
//	
//	@Test
//	void getRestaurantsByName_Failed() {
//		String name = "Saffron spice";
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantRepository.findByName(name)).thenThrow(new RuntimeException());
//		
//		assertThrows(RestaurantException.class, () -> {serviceImpl.getRestaurantsByName(name, pageNumber, pageSize);});
//		
//	}
//	
//	@Test
//	void getRestaurantsByRating_Success() {
//		Double rating = 4.2;
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantRepository.findByRating(rating)).thenReturn(restaurants);
//		
//		List<Restaurant> returnedRestaurants = serviceImpl.getRestaurantsByRating(rating, pageNumber, pageSize);
//		assertThat(returnedRestaurants.equals(restaurants));
//		
//	}
//	
//	@Test
//	void getRestaurantsByRating_Failed() {
//		Double rating = 4.2;
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantRepository.findByRating(rating)).thenThrow(new RuntimeException());
//		
//		assertThrows(RestaurantException.class, () -> {serviceImpl.getRestaurantsByRating(rating, pageNumber, pageSize);});
//		
//	}
//	
//	@Test
//	void getRestaurantsByLocationCuisine_Success() {
//		String location = "Jatni";
//		String cuisine = "Indian veg";
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantRepository.findByLocationAndCuisine(location, cuisine)).thenReturn(restaurants);
//		
//		List<Restaurant> returnedRestaurants = serviceImpl.getRestaurantsByLocationAndCuisine(location, cuisine, pageNumber, pageSize);
//		assertThat(returnedRestaurants.equals(restaurants));
//		
//	}
//	
//	@Test
//	void getRestaurantsByLocationCuisine_Failed() {
//		String location = "Jatni";
//		String cuisine = "Indian veg";
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantRepository.findByLocationAndCuisine(location, cuisine)).thenThrow(new RuntimeException());
//		
//		assertThrows(RestaurantException.class, () -> {serviceImpl.getRestaurantsByLocationAndCuisine(location, cuisine, pageNumber, pageSize);});
//		
//	}
//	
//	@Test
//	void getRestaurantsByNameAndLocation_Success() {
//		String name = "Saffron spice";
//		String location = "Jatni";
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantRepository.findByNameAndLocation(name, location)).thenReturn(restaurants);
//		
//		List<Restaurant> returnedRestaurants = serviceImpl.getRestaurantsByNameAndLocation(name, location, pageNumber, pageSize);
//		assertThat(returnedRestaurants.equals(restaurants));
//		
//	}
//	
//	@Test
//	void getRestaurantsByNameAndLocation_Failed() {
//		String name = "Saffron spice";
//		String location = "Jatni";
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantRepository.findByNameAndLocation(name, location)).thenThrow(new RuntimeException());
//		
//		assertThrows(RestaurantException.class, () -> {serviceImpl.getRestaurantsByNameAndLocation(name, location, pageNumber, pageSize);});
//		
//	}
//	
//	@Test
//	void getItemByItemId_Success() {
//		Long menuId = 1l;
//		
//		when(menuItemRepository.findById(menuId)).thenReturn(Optional.of(menuItems.get(0)));
//		
//		MenuItem menuItem = serviceImpl.getItemByItemId(menuId);
//		assertThat(menuItem.equals(menuItems.get(0)));
//		
//	}
//	
//	@Test
//	void getItemByItemId_EmptyResult() {
//		Long menuId = 1l;
//		
//		when(menuItemRepository.findById(menuId)).thenReturn(Optional.empty());
//		
//		MenuItem menuItem = serviceImpl.getItemByItemId(menuId);
//		assertThat(menuItem == null);
//		
//	}
//	
//	@Test
//	void getItemByItemId_Failed() {
//		Long menuId = 1l;
//		
//		when(menuItemRepository.findById(menuId)).thenThrow(new RuntimeException());
//		
//		assertThrows(RestaurantException.class, () -> {serviceImpl.getItemByItemId(menuId);});
//		
//	}
//	
//	@Test
//	void updateRestaurantRating_Success() {
//		Long restaurantId = 1l;
//		Double rating = 4.5;
//		
//		when(restaurantRepository.updateRating(restaurantId, rating)).thenReturn(1);
//		
//		Integer status = serviceImpl.updateRestaurantRating(restaurantId, rating);
//		assertThat(status == 1);
//		
//	}
//	
//	@Test
//	void updateRestaurantRating_SQLException() {
//		Long restaurantId = 1l;
//		Double rating = 4.5;
//		
//		when(restaurantRepository.updateRating(restaurantId, rating)).thenThrow(new RuntimeException());
//		
//		assertThrows(RestaurantException.class, () -> {serviceImpl.updateRestaurantRating(restaurantId, rating);});
//		
//	}
//
//}
