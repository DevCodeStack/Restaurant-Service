package com.eatza.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eatza.restaurant.dto.CourseAndHourDto;
import com.eatza.restaurant.dto.CuisineRequestDto;
import com.eatza.restaurant.exception.RestaurantException;
import com.eatza.restaurant.model.Course;
import com.eatza.restaurant.model.Cuisine;
import com.eatza.restaurant.model.Restaurant;
import com.eatza.restaurant.service.CuisineService;
import com.eatza.restaurant.service.RestaurantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
//@RequestMapping("/cuisine")
@Slf4j
public class CuisineController {
	
	@Autowired
	CuisineService cuisineService;
	
	@GetMapping("/cuisine")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(tags = "CuisineController", description = "Get all cuisines")
	public ResponseEntity<List<Cuisine>> getAllCuisines(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws RestaurantException {
		log.info("In getAllCuisiness method");
		return ResponseEntity.status(HttpStatus.OK).body(cuisineService.getAllCuisines(pageNumber, pageSize));
	}
	
	@PostMapping("/cuisine")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(tags = "RestaurantController", description = "Add new cuisine")
	public ResponseEntity<List<Cuisine>> addCuisines(@Valid @RequestBody CuisineRequestDto requestDto) throws RestaurantException {
		log.info("In add Cuisines method");
		return ResponseEntity.status(HttpStatus.OK).body(cuisineService.addCuisines(requestDto));
	}
	
	@PostMapping("/cuisine/upload")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(tags = "RestaurantController", description = "Upload new cuisine")
	public ResponseEntity<List<Cuisine>> uploadCuisines(@RequestParam MultipartFile file) throws RestaurantException {
		log.info("In upload Cuisines method");
		if (file.isEmpty()) {
            throw new RestaurantException("Please upload an Excel file.");
        }
		return ResponseEntity.status(HttpStatus.OK).body(cuisineService.uploadCuisines(file));
	}
	
	@GetMapping("/course")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(tags = "CuisineController", description = "Get all courses")
	public ResponseEntity<List<Course>> getAllCourses(@RequestParam Long cuisineId, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws RestaurantException {
		log.info("In getAllCourses method");
		return ResponseEntity.status(HttpStatus.OK).body(cuisineService.getAllCourses(cuisineId, pageNumber, pageSize));
	}
	
	@PostMapping("/course")
	@SecurityRequirement(name = "BearerAuth")
	@Operation(tags = "RestaurantController", description = "Add new course")
	public ResponseEntity<Course> addCourse(@Valid @RequestBody CourseAndHourDto courseAndHourDto) throws RestaurantException {
		log.info("In add Course method");
		return ResponseEntity.status(HttpStatus.OK).body(cuisineService.addCourse(courseAndHourDto));
	}

}
