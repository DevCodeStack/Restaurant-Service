package com.eatza.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eatza.restaurant.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
