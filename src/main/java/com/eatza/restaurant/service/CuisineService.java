package com.eatza.restaurant.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eatza.restaurant.dto.CourseAndHourDto;
import com.eatza.restaurant.dto.CuisineRequestDto;
import com.eatza.restaurant.model.Course;
import com.eatza.restaurant.model.Cuisine;

public interface CuisineService {
	
	List<Cuisine> getAllCuisines(Integer pageNumber, Integer pageSize);
	List<Cuisine> addCuisines(CuisineRequestDto requestDto);
	List<Cuisine> uploadCuisines(MultipartFile file);
	List<Course> getAllCourses(Long cuisineId, Integer pageNumber, Integer pageSize);
	Course addCourse(CourseAndHourDto courseAndHourDto);
	
}
