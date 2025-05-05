//package com.eatza.restaurant.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.eatza.restaurant.dto.ErrorResponseDto;
//import com.eatza.restaurant.exception.CustomGlobalExceptionHandler;
//import com.eatza.restaurant.exception.InvalidTokenException;
//import com.eatza.restaurant.exception.RestaurantException;
//import com.eatza.restaurant.exception.UnauthorizedException;
//import com.eatza.restaurant.model.Menu;
//import com.eatza.restaurant.model.MenuItem;
//import com.eatza.restaurant.model.Restaurant;
//import com.eatza.restaurant.service.RestaurantService;
//import com.eatza.restaurant.util.ErrorCodesEnum;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@ExtendWith(MockitoExtension.class)
//class RestaurantControllerTest {
//	
//	private MockMvc mockMvc;
//	
//	@Mock
//	RestaurantService restaurantService;
//	
//	@InjectMocks
//	RestaurantController restaurantController;
//	
//	private List<MenuItem> menuItems;
//	
//	private List<Restaurant> restaurants;
//	
//	private JacksonTester<Restaurant> jsonRestaurant;
//	
//	private JacksonTester<MenuItem> jsonMenuItem;
//	
//	private JacksonTester<List<Restaurant>> jsonRestaurantList;
//	
//	private JacksonTester<List<MenuItem>> jsonMenuItemList;	
//	
//	private JacksonTester<ErrorResponseDto> jsonErrorResponseDto;
//
//	@BeforeEach
//	void setUp() throws Exception {
//		
//		mockMvc = MockMvcBuilders.standaloneSetup(restaurantController)
//				.setControllerAdvice(new CustomGlobalExceptionHandler())
//				.build();
//		
//		JacksonTester.initFields(this, new ObjectMapper());
//		
//		Restaurant restaurant = new Restaurant();
//		restaurant.setId(1l);
//		restaurant.setName("Hotel Tridev");
//		restaurant.setLocation("Jatni");
//		restaurant.setCuisine("Indian veg");
//		restaurant.setBudget(1000);
//		restaurant.setRating(4.2);
//		
//		Restaurant newRestaurant = new Restaurant();
//		newRestaurant.setId(2l);
//		newRestaurant.setName("Saffron spice");
//		newRestaurant.setLocation("Jatni");
//		newRestaurant.setCuisine("Indian mixed");
//		newRestaurant.setBudget(2000);
//		newRestaurant.setRating(4.2);
//		
//		Menu morningMenu = new Menu();
//		morningMenu.setId(1l);
//		morningMenu.setActiveFrom("7");
//		morningMenu.setActiveTill("10");
//		morningMenu.setRestaurant(restaurant);
//		
//		Menu afternoonMenu = new Menu();
//		afternoonMenu.setId(2l);
//		afternoonMenu.setActiveFrom("12");
//		afternoonMenu.setActiveTill("14");
//		afternoonMenu.setRestaurant(restaurant);
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
//		MenuItem afternoonMenuItem1 = new MenuItem();
//		afternoonMenuItem1.setId(3l);
//		afternoonMenuItem1.setName("Rice");
//		afternoonMenuItem1.setDescription("Plain Rice");
//		afternoonMenuItem1.setPrice(15);
//		afternoonMenuItem1.setMenu(afternoonMenu);
//		
//		MenuItem afternoonMenuItem2 = new MenuItem();
//		afternoonMenuItem2.setId(4l);
//		afternoonMenuItem2.setName("Rice");
//		afternoonMenuItem2.setDescription("Curd Rice");
//		afternoonMenuItem2.setPrice(35);
//		afternoonMenuItem2.setMenu(afternoonMenu);
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
//	}
//
//	//Positive test case : getItemsByRestaurantId
//	@Test
//	void getItemsByRestaurantId_Success() throws Exception {
//		Long restaurantId = 1l;
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantService.getItemsByRestaurantId(any(), any(), any())).thenReturn(menuItems);
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/restaurant/items/"+restaurantId).accept(MediaType.APPLICATION_JSON)
//				.param("pageNumber", pageNumber.toString())
//				.param("pageSize", pageSize.toString()))
//				.andReturn().getResponse();
//		
//		List<MenuItem> returnedMenuItems = jsonMenuItemList.parseObject(response.getContentAsString());
//		 assertEquals(menuItems.size(), returnedMenuItems.size());
//	}
//	
//	//Negative test case : getItemsByRestaurantId
//	@Test
//	void getItemsByRestaurantId_Failed() throws Exception {
//		Long restaurantId = 1l;
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantService.getItemsByRestaurantId(any(), any(), any())).thenThrow(new RestaurantException());
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/restaurant/items/"+restaurantId).accept(MediaType.APPLICATION_JSON)
//				.param("pageNumber", pageNumber.toString())
//				.param("pageSize", pageSize.toString()))
//				.andReturn().getResponse();
//		
//		ErrorResponseDto responseDto = jsonErrorResponseDto.parseObject(response.getContentAsString());
//		assertEquals("EX500", responseDto.getCode());
//	}
//	
//	//Positive test case : getAllRestaurants
//	@Test
//	void getAllRestaurants_Success() throws Exception {
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantService.getAllRestaurants(any(), any())).thenReturn(restaurants);
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/restaurants").accept(MediaType.APPLICATION_JSON)
//				.param("pageNumber", pageNumber.toString())
//				.param("pageSize", pageSize.toString()))
//				.andReturn().getResponse();
//		
//		List<Restaurant> returnedRestaurants = jsonRestaurantList.parseObject(response.getContentAsString());
//		 assertEquals(restaurants.size(), returnedRestaurants.size());
//	}
//	
//	//Negative test case : getAllRestaurants
//	@Test
//	void getAllRestaurants_Failed() throws Exception {
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantService.getAllRestaurants(any(), any())).thenThrow(new RestaurantException());
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/restaurants").accept(MediaType.APPLICATION_JSON)
//				.param("pageNumber", pageNumber.toString())
//				.param("pageSize", pageSize.toString()))
//				.andReturn().getResponse();
//		
//		ErrorResponseDto responseDto = jsonErrorResponseDto.parseObject(response.getContentAsString());
//		assertEquals("EX500", responseDto.getCode());
//	}
//	
//	//Positive test case : getRestaurantById
//	@Test
//	void getRestaurantById_Success() throws Exception {
//		Long restaurantId = 1l;
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantService.getRestaurantById(any())).thenReturn(restaurants.get(0));
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/restaurants/"+restaurantId).accept(MediaType.APPLICATION_JSON)
//				.param("pageNumber", pageNumber.toString())
//				.param("pageSize", pageSize.toString()))
//				.andReturn().getResponse();
//		Restaurant returnedRestaurant = jsonRestaurant.parseObject(response.getContentAsString());
//		 assertEquals(restaurants.get(0).getName(), returnedRestaurant.getName());
//		 assertEquals(restaurants.get(0).getLocation(), returnedRestaurant.getLocation());
//		 assertEquals(restaurants.get(0).getRating(), returnedRestaurant.getRating());
//		 assertEquals(restaurants.get(0).getCuisine(), returnedRestaurant.getCuisine());
//		 assertEquals(restaurants.get(0).getBudget(), returnedRestaurant.getBudget());
//	}
//	
//	//Negative test case : getRestaurantById
//	@Test
//	void getRestaurantById_Failed() throws Exception {
//		Long restaurantId = 1l;
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantService.getRestaurantById(any())).thenThrow(new RestaurantException());
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/restaurants/"+restaurantId).accept(MediaType.APPLICATION_JSON)
//				.param("pageNumber", pageNumber.toString())
//				.param("pageSize", pageSize.toString()))
//				.andReturn().getResponse();
//		
//		ErrorResponseDto responseDto = jsonErrorResponseDto.parseObject(response.getContentAsString());
//		assertEquals("EX500", responseDto.getCode());
//	}
//	
//	//Positive test case : getRestaurantsByBudget
//	@Test
//	void getRestaurantsByBudget_Success() throws Exception {
//		Integer budget = 1000;
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantService.getRestaurantsByBudget(any(), any(), any())).thenReturn(restaurants);
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/restaurants/budget/"+budget).accept(MediaType.APPLICATION_JSON)
//				.param("pageNumber", pageNumber.toString())
//				.param("pageSize", pageSize.toString()))
//				.andReturn().getResponse();
//		
//		List<Restaurant> returnedRestaurants = jsonRestaurantList.parseObject(response.getContentAsString());
//		 assertEquals(restaurants.size(), returnedRestaurants.size());
//	}
//	
//	//Negative test case : getRestaurantsByBudget
//	@Test
//	void getRestaurantsByBudget_Failed() throws Exception {
//		Integer budget = 1000;
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantService.getRestaurantsByBudget(any(), any(), any())).thenThrow(new RestaurantException());
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/restaurants/budget/"+budget).accept(MediaType.APPLICATION_JSON)
//				.param("pageNumber", pageNumber.toString())
//				.param("pageSize", pageSize.toString()))
//				.andReturn().getResponse();
//		
//		ErrorResponseDto responseDto = jsonErrorResponseDto.parseObject(response.getContentAsString());
//		assertEquals("EX500", responseDto.getCode());
//	}
//	
//	//Positive test case : getRestaurantsByName
//	@Test
//	void getRestaurantsByName_Success() throws Exception {
//		String name = "Saffron spice";
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantService.getRestaurantsByName(any(), any(), any())).thenReturn(restaurants);
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/restaurants/name/"+name).accept(MediaType.APPLICATION_JSON)
//				.param("pageNumber", pageNumber.toString())
//				.param("pageSize", pageSize.toString()))
//				.andReturn().getResponse();
//		
//		List<Restaurant> returnedRestaurants = jsonRestaurantList.parseObject(response.getContentAsString());
//		 assertEquals(restaurants.size(), returnedRestaurants.size());
//	}
//	
//	//Negative test case : getRestaurantsByName
//	@Test
//	void getRestaurantsByName_Failed() throws Exception {
//		String name = "Saffron spice";
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantService.getRestaurantsByName(any(), any(), any())).thenThrow(new RestaurantException());
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/restaurants/name/"+name).accept(MediaType.APPLICATION_JSON)
//				.param("pageNumber", pageNumber.toString())
//				.param("pageSize", pageSize.toString()))
//				.andReturn().getResponse();
//		
//		ErrorResponseDto responseDto = jsonErrorResponseDto.parseObject(response.getContentAsString());
//		assertEquals("EX500", responseDto.getCode());
//	}
//	
//	//Positive test case : getRestaurantsByRating
//	@Test
//	void getRestaurantsByRating_Success() throws Exception {
//		Double rating = 4.2;
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantService.getRestaurantsByRating(any(), any(), any())).thenReturn(restaurants);
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/restaurants/rating/"+rating).accept(MediaType.APPLICATION_JSON)
//				.param("pageNumber", pageNumber.toString())
//				.param("pageSize", pageSize.toString()))
//				.andReturn().getResponse();
//		
//		List<Restaurant> returnedRestaurants = jsonRestaurantList.parseObject(response.getContentAsString());
//		 assertEquals(restaurants.size(), returnedRestaurants.size());
//	}
//	
//	//Negative test case : getRestaurantsByRating
//	@Test
//	void getRestaurantsByRating_Failed() throws Exception {
//		Double rating = 4.2;
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantService.getRestaurantsByRating(any(), any(), any())).thenThrow(new RestaurantException());
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/restaurants/rating/"+rating).accept(MediaType.APPLICATION_JSON)
//				.param("pageNumber", pageNumber.toString())
//				.param("pageSize", pageSize.toString()))
//				.andReturn().getResponse();
//		
//		ErrorResponseDto responseDto = jsonErrorResponseDto.parseObject(response.getContentAsString());
//		assertEquals("EX500", responseDto.getCode());
//	}
//	
//	//Positive test case : getRestaurantsByLocationAndCuisine
//	@Test
//	void getRestaurantsByLocationAndCuisine_Success() throws Exception {
//		String location = "Jatni";
//		String cuisine = "Indian mixed";
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantService.getRestaurantsByLocationAndCuisine(any(), any(), any(), any())).thenReturn(restaurants);
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/restaurants/location/"+location+"/cuisine/"+cuisine).accept(MediaType.APPLICATION_JSON)
//				.param("pageNumber", pageNumber.toString())
//				.param("pageSize", pageSize.toString()))
//				.andReturn().getResponse();
//		
//		List<Restaurant> returnedRestaurants = jsonRestaurantList.parseObject(response.getContentAsString());
//		 assertEquals(restaurants.size(), returnedRestaurants.size());
//	}
//	
//	//Negative test case : getRestaurantsByLocationAndCuisine
//	@Test
//	void getRestaurantsByLocationAndCuisine_Failed() throws Exception {
//		String location = "Jatni";
//		String cuisine = "Indian mixed";
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		InvalidTokenException exception = new InvalidTokenException();
//		
//		when(restaurantService.getRestaurantsByLocationAndCuisine(any(), any(), any(), any())).thenThrow(exception);
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/restaurants/location/"+location+"/cuisine/"+cuisine).accept(MediaType.APPLICATION_JSON)
//				.param("pageNumber", pageNumber.toString())
//				.param("pageSize", pageSize.toString()))
//				.andReturn().getResponse();
//		
//		ErrorResponseDto responseDto = jsonErrorResponseDto.parseObject(response.getContentAsString());
//		assertEquals("EX500", responseDto.getCode());
//	}
//	
//	//Positive test case : getRestaurantsByLocationAndName
//	@Test
//	void getRestaurantsByLocationAndName_Success() throws Exception {
//		String location = "Jatni";
//		String name = "Saffron spice";
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		when(restaurantService.getRestaurantsByNameAndLocation(any(), any(), any(), any())).thenReturn(restaurants);
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/restaurants/name/"+name+"/location/"+location).accept(MediaType.APPLICATION_JSON)
//				.param("pageNumber", pageNumber.toString())
//				.param("pageSize", pageSize.toString()))
//				.andReturn().getResponse();
//		
//		List<Restaurant> returnedRestaurants = jsonRestaurantList.parseObject(response.getContentAsString());
//		 assertEquals(restaurants.size(), returnedRestaurants.size());
//	}
//	
//	//Negative test case : getRestaurantsByLocationAndName
//	@Test
//	void getRestaurantsByLocationAndName_Failed() throws Exception {
//		String location = "Jatni";
//		String name = "Saffron spice";
//		Integer pageNumber = 1;
//		Integer pageSize = 10;
//		
//		UnauthorizedException exception = new UnauthorizedException();
//		
//		when(restaurantService.getRestaurantsByNameAndLocation(any(), any(), any(), any())).thenThrow(exception);
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/restaurants/name/"+name+"/location/"+location).accept(MediaType.APPLICATION_JSON)
//				.param("pageNumber", pageNumber.toString())
//				.param("pageSize", pageSize.toString()))
//				.andReturn().getResponse();
//		
//		ErrorResponseDto responseDto = jsonErrorResponseDto.parseObject(response.getContentAsString());
//		assertEquals("EX500", responseDto.getCode());
//	}
//	
//	//Positive test case : getItemByItemId
//	@Test
//	void getItemByItemId_Success() throws Exception {
//		Long itemId = 1l;
//		
//		when(restaurantService.getItemByItemId(any())).thenReturn(menuItems.get(0));
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/item/"+itemId).accept(MediaType.APPLICATION_JSON))
//				.andReturn().getResponse();
//		
//		MenuItem returnedMenuItem = jsonMenuItem.parseObject(response.getContentAsString());
//		 assertEquals(menuItems.get(0).getName(), returnedMenuItem.getName());
//		 assertEquals(menuItems.get(0).getName(), returnedMenuItem.getName());
//		 assertEquals(menuItems.get(0).getPrice(), returnedMenuItem.getPrice());
//		 assertEquals(menuItems.get(0).getMenu().getId(), returnedMenuItem.getMenu().getId());
//		 assertEquals(menuItems.get(0).getDescription(), returnedMenuItem.getDescription());
//	}
//	
//	//Negative test case : getItemByItemId
//	@Test
//	void getItemByItemId_Failed() throws Exception {
//		Long itemId = 1l;
//		
//		InvalidTokenException exception = new InvalidTokenException("Exception occured", ErrorCodesEnum.INTERNAL_SERVER_ERROR);
//		
//		when(restaurantService.getItemByItemId(any())).thenThrow(exception);
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get("/item/"+itemId).accept(MediaType.APPLICATION_JSON))
//				.andReturn().getResponse();
//		
//		ErrorResponseDto responseDto = jsonErrorResponseDto.parseObject(response.getContentAsString());
//		assertEquals("EX500", responseDto.getCode());
//	}
//	
//	//Positive test case : updateRestaurantRating
//	@Test
//	void updateRestaurantRating_Success() throws Exception {
//		Long restaurantId = 1l;
//		Double rating = 4.2;
//		
//		when(restaurantService.updateRestaurantRating(any(), any())).thenReturn(1);
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				put("/restaurant/"+restaurantId+"/review").accept(MediaType.APPLICATION_JSON)
//				.param("rating", rating.toString()))
//				.andReturn().getResponse();
//		
//		String count = response.getContentAsString();
//		 assertEquals(1, Integer.parseInt(count));
//	}
//	
//	//Negative test case : updateRestaurantRating
//	@Test
//	void updateRestaurantRating_Failed() throws Exception {
//		Long restaurantId = 1l;
//		Double rating = 4.2;
//		
//		RestaurantException exception = new RestaurantException("Exception occured", ErrorCodesEnum.INTERNAL_SERVER_ERROR);
//		
//		when(restaurantService.updateRestaurantRating(any(), any())).thenThrow(exception);
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				put("/restaurant/"+restaurantId+"/review").accept(MediaType.APPLICATION_JSON)
//				.param("rating", rating.toString()))
//				.andReturn().getResponse();
//		
//		ErrorResponseDto responseDto = jsonErrorResponseDto.parseObject(response.getContentAsString());
//		assertEquals("EX500", responseDto.getCode());
//	}
//
//}
