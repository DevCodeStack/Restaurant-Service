package com.eatza.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eatza.restaurant.model.ActiveHours;

@Repository
public interface ActiveHoursRepository extends JpaRepository<ActiveHours, Long> {

}
