package com.kafka.order.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kafka.order.model.OrderEvent;



@Service
public class OrderProducer {

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
    
    @org.springframework.beans.factory.annotation.Value("${kafka.topic.order}")
    private String topic;

    public void sendOrderEvent(OrderEvent event) {

        kafkaTemplate.send(topic, String.valueOf(event.orderId()), event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        System.out.println("✅ Message sent: " + event.orderId());
                    } else {
                        System.out.println("❌ Failed to send: " + ex.getMessage());
                    }
                });
    }
}