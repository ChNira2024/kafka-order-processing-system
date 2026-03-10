package com.kafka.order.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.order.config.KafkaConfig;
import com.kafka.order.entity.Order;
import com.kafka.order.entity.TrackingEvent;
import com.kafka.order.model.OrderEvent;
import com.kafka.order.repository.OrderRepository;
import com.kafka.order.repository.TrackingRepo;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private TrackingRepo trackingRepo;

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        order.setStatus("PENDING");
        orderRepo.save(order);

        // Send event to Kafka
        OrderEvent event = new OrderEvent(order.getId(), order.getStatus(), LocalDateTime.now());

        kafkaTemplate.send(KafkaConfig.ORDER_TOPIC, event);
        System.out.println("Order event sent: " + event);

        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}/tracking")
    public ResponseEntity<List<TrackingEvent>> getTracking(@PathVariable("id") Long id){
    	System.out.println("id :"+id);
    	List<TrackingEvent> data =  trackingRepo.findByOrderIdOrderByTimestampAsc(id);
    	return ResponseEntity.ok(data);
    }
}
