package com.kafka.payment.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.payment.model.OrderEvent;

@Service
public class PaymentDLQConsumer {

    @KafkaListener(topics = "inventory-topic.DLT", groupId = "payment-dlq-group")
    public void handleDLQ(OrderEvent event) {

        System.out.println("🚨 Payment DLQ received: " + event.orderId());

        // store in DB / alert system
    }
}