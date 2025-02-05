package com.eatza.restaurant;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.eatza.restaurant.config.JwtFilter;
import com.eatza.restaurant.util.JwtTokenUtil;

@SpringBootApplication
@EnableScheduling
@EnableWebMvc
public class RestaurantServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantServiceApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<JwtFilter> filterRegistration(JwtTokenUtil tokenUtil){
		
		FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFilter(tokenUtil));
		registrationBean.setUrlPatterns(Arrays.asList("/restaurant/*", "/restaurants/*", "/item/*"));
		return registrationBean;
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
