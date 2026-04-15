package com.kafka.order.service;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafka.order.entity.Order;
import com.kafka.order.enums.OrderStatus;
import com.kafka.order.model.OrderEvent;
import com.kafka.order.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderProducer producer;

    public Order createOrder(OrderEvent event) {

        // ✅ Save in DB first (important for reliability)
    	Order order = new Order();
    	order.setAmount(event.amount());
    	order.setStatus(OrderStatus.CREATED);
    	order.setCreatedAt(LocalDateTime.now());
    	
     // Step 2: Save and get ID
        Order savedOrder = repository.save(order);

        // Step 3: Create event with DB ID
        OrderEvent newEvent = new OrderEvent(
        	    savedOrder.getOrderId(),
        	    savedOrder.getStatus().name(),
        	    savedOrder.getAmount(),
        	    savedOrder.getCreatedAt()
        	);

        // ✅ Send event to Kafka
        producer.sendOrderEvent(newEvent);

        return order;
    }
}