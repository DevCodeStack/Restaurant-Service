package com.eatza.restaurant;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import com.eatza.restaurant.config.JwtFilter;
import com.eatza.restaurant.util.JwtTokenUtil;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceApplicationTests {
	
	@Mock
	RestaurantServiceApplication restaurantServiceApplication;
	
	JwtTokenUtil tokenUtil;

	@Test
	void contextLoads() {
		
		restaurantServiceApplication = new RestaurantServiceApplication();
		
		FilterRegistrationBean<JwtFilter> registrationBean = 
				restaurantServiceApplication.filterRegistration(tokenUtil);
		assertThat(registrationBean.getUrlPatterns().equals(Arrays.array("/restaurant/*", "/restaurants/*", "/item/*")));
	}

}
