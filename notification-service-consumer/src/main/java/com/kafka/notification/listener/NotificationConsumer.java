package com.kafka.notification.listener;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.notification.enums.OrderStatus;
import com.kafka.notification.model.OrderEvent;
import com.kafka.notification.service.EmailService;
@Service
public class NotificationConsumer {
	@Autowired
    private EmailService emailService;

	
    @KafkaListener(topics = "payment-topic", groupId = "notification-group")
    public void consume(OrderEvent event) {

        if (event.status() == OrderStatus.PAYMENT_SUCCESS) {
            sendEmailSuccess(event);
        } else {
            sendEmailFailure(event);
        }
    }
    
    private void sendEmailSuccess(OrderEvent event) {

        emailService.sendEmail(
                "chartyniranjana2016@gmail.com",
                "Order Confirmed",
                "Your order " + event.orderId() + " is successful"
        );
    }

    private void sendEmailFailure(OrderEvent event) {
        System.out.println("📩 EMAIL SENT: Order " + event.orderId() + " failed.");
    }
}