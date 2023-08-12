package com.eatza.restaurant.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.eatza.restaurant.dto.Order;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaProducer {
	
	@Autowired
	KafkaTemplate<String, Order> kafkaTemplate;
	
	public void publishOrder(Order order) {
		try {
		kafkaTemplate.send("topicrestaurant", order);
		log.debug("Order published");
		}catch(Exception ex) {
			log.debug("Error while publishing updated order : ", order.getId());
		}
	}

}
