package com.kafka.notification.listener;



import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.notification.entity.TrackingEvent;
import com.kafka.notification.model.PaymentEvent;
import com.kafka.notification.repo.TrackingRepo;

@RestController
public class NotificationController {

    @Autowired
    private TrackingRepo trackingRepo;

    @KafkaListener(topics = "payment-events", groupId = "notification-group")
    public void consumePaymentEvent(PaymentEvent paymentEvent) {
        // Update tracking
        TrackingEvent tracking = new TrackingEvent();
        tracking.setOrderId(paymentEvent.orderId());
        tracking.setStatus(paymentEvent.status());
        tracking.setLocation("Final Delivery Location");
        tracking.setTimestamp(LocalDateTime.now());

        trackingRepo.save(tracking);

        // Simulate sending notification
        System.out.println("Notification sent for Order ID: " + paymentEvent.orderId() +
                ", Status: " + paymentEvent.status());
    }
}
