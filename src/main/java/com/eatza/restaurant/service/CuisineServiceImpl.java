package com.eatza.restaurant.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eatza.restaurant.dto.CourseAndHourDto;
import com.eatza.restaurant.dto.CuisineRequestDto;
import com.eatza.restaurant.exception.RestaurantException;
import com.eatza.restaurant.model.ActiveHours;
import com.eatza.restaurant.model.Course;
import com.eatza.restaurant.model.Cuisine;
import com.eatza.restaurant.repository.ActiveHoursRepository;
import com.eatza.restaurant.repository.CourseRepository;
import com.eatza.restaurant.repository.CuisineRepository;

import jakarta.transaction.Transactional;

@Service
public class CuisineServiceImpl implements CuisineService {
	
	@Autowired
	private CuisineRepository cuisineRepository;
	
	@Autowired
	private ActiveHoursRepository activeHoursRepository;
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<Cuisine> getAllCuisines(Integer pageNumber, Integer pageSize) {
		
		return null;
	}

	@Override
	@Transactional
	public List<Cuisine> addCuisines(CuisineRequestDto requestDto) {
		
		return requestDto.getNames().parallelStream()
						.filter(name -> cuisineRepository.findByName(name).get().size() < 1)
//						.peek(name -> System.out.println(name+ " is filtered to be inserted"))
						.map(Cuisine.builder()::name)
						.map(builder -> builder.build())
						.map(cuisineRepository::save)
						.toList();
	}
	
	@Override
	@Transactional
	public List<Cuisine> uploadCuisines(MultipartFile file) {
		List<Cuisine> requestedCuisines = new ArrayList<>();
		try(InputStream is = file.getInputStream()){
			
			Workbook workbook = WorkbookFactory.create(is);
	        Sheet sheet = workbook.getSheetAt(0);
	        
	        for(Row row : sheet) {
	        	if (row.getRowNum() == 0) continue; // Skip header
	        	
	        	requestedCuisines.add(Cuisine.builder().name(row.getCell(1).getStringCellValue()).build());
	        }
	        
		} catch(IOException ioe) {
			
		}
		
		return cuisineRepository.saveAll(requestedCuisines);
	}
	
	@Override
	public List<Course> getAllCourses(Long cuisineId, Integer pageNumber, Integer pageSize) {
		
//		courseRepository.findById(cuisineId);
		
		return null;
	}
	
	@Override
	@Transactional
	public Course addCourse(CourseAndHourDto courseAndHourDto) {
		
		Optional<Cuisine> optCuisine = cuisineRepository.findById(courseAndHourDto.getCuisineId());
		if (optCuisine.isEmpty()) {
			throw new RestaurantException("No such cuisine available!");
		}
		
		ActiveHours activeHours = 
				ActiveHours.builder()
						.activeFrom(courseAndHourDto.getActiveFrom())
						.activeTill(courseAndHourDto.getActiveTill())
						.build();
		
		activeHoursRepository.save(activeHours);
		
		Course course = Course.builder()
				.cuisine(optCuisine.get())
				.activeHours(activeHours)
				.name(courseAndHourDto.getName())
				.build();
		
		return courseRepository.saveAndFlush(course);
	}

}
