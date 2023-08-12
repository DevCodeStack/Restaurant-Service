package com.eatza.restaurant.util;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;

import com.eatza.restaurant.dto.Order;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class OrderGlobalScope {
	
	public static List<Order> orderInProcessList = new CopyOnWriteArrayList<>();;
	
	public static List<Order> orderProcessedList = new CopyOnWriteArrayList<>();;

}
