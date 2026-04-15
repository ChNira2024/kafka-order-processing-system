package com.kafka.payment.listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.kafka.payment.enums.OrderStatus;
import com.kafka.payment.model.OrderEvent;
@Service
public class PaymentConsumer {

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @RetryableTopic(
            attempts = "3",
            backOff = @BackOff(delay = 3000) 
    )
    @KafkaListener(topics = "inventory-topic", groupId = "payment-group")
    public void consume(OrderEvent event, Acknowledgment ack) {

        System.out.println("💰 Payment processing for Order: " + event.orderId());

        boolean paymentSuccess = processPayment(event);

        if (!paymentSuccess) {
            throw new RuntimeException("Payment Failed");
        }

        // ✅ SUCCESS FLOW
        OrderEvent updated = new OrderEvent(
                event.orderId(),
                OrderStatus.PAYMENT_SUCCESS,
                event.productId(),
                event.quantity(),
                event.amount(),
                event.timestamp()
        );

        kafkaTemplate.send(
                "payment-topic",
                String.valueOf(event.orderId()),
                updated
        );

        ack.acknowledge();
    }

    private boolean processPayment(OrderEvent event) {
        // simulate payment failure/success
        return Math.random() > 0.3; // 70% success rate
    }
}