package com.kafka.payment.listener;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kafka.payment.config.KafkaConfig;
import com.kafka.payment.model.InventoryEvent;
import com.kafka.payment.model.PaymentEvent;

@Component
public class PaymentController {

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    @KafkaListener(topics = "inventory-events", groupId = "payment-group")
    public void consumeInventoryEvent(InventoryEvent inventoryEvent) {
    	
        // Simulate payment processing
        String paymentStatus = "PAID"; // could also fail for demo

        PaymentEvent paymentEvent = new PaymentEvent(inventoryEvent.orderId(),paymentStatus,LocalDateTime.now());

        // Send payment event to Kafka
        kafkaTemplate.send(KafkaConfig.PAYMENT_TOPIC, paymentEvent);

        System.out.println("Payment processed for Order ID: " + inventoryEvent.orderId() + ", Status: " + paymentStatus);
    }
}
