package com.eatza.restaurant.kafka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.eatza.restaurant.dto.Order;
import com.eatza.restaurant.util.OrderGlobalScope;

import lombok.extern.slf4j.Slf4j;

//@Service
@Slf4j
public class KafkaService {
	
	@Autowired
	KafkaProducer kafkaProducer;
	
	public void updateOrderStatus(Order order) {
		log.debug("Inside updateOrderStatus");
		try {
			Boolean status = false;
			System.err.println("Order status: "+order.getStatus());
			if(order.getStatus().equalsIgnoreCase("CREATED") || order.getStatus().equalsIgnoreCase("UPDATED"))
				status = OrderGlobalScope.orderInProcessList.add(order);
			
			System.err.println("In process list: "+status);
			System.err.println(OrderGlobalScope.orderInProcessList);
			
			if(order.getStatus().equalsIgnoreCase("PROCESSING"))
				status = OrderGlobalScope.orderProcessedList.add(order);
			
			System.err.println("Processed list: "+status);
			System.err.println(OrderGlobalScope.orderProcessedList);
		
		} catch(Exception ex) {
			log.info("Error occured : ", ex.getMessage());
		}
	}
	
	@Scheduled(fixedDelay = 60000)
	public void scheduleInProcess() {
		log.debug("scheduler[in process] executing");

		List<Order> orderList = OrderGlobalScope.orderInProcessList;
		for(Order orderDto : orderList) {
			orderDto.setStatus("PROCESSING");
			kafkaProducer.publishOrder(orderDto);
			log.debug("Order status updated: ", orderDto.getId());
			orderList.remove(orderDto);
		}

	}
	
	@Scheduled(fixedDelay = 120000)
	public void scheduleProcessed() {
		log.debug("scheduler[processed] executing");
		log.debug("Order count: ", OrderGlobalScope.orderProcessedList.size());

		List<Order> orderList = OrderGlobalScope.orderProcessedList;
		for(Order orderDto : orderList) {
			orderDto.setStatus("COMPLETED");
			kafkaProducer.publishOrder(orderDto);
			log.debug("Order status updated: ", orderDto.getId());
			orderList.remove(orderDto);
		}

	}
	
}
