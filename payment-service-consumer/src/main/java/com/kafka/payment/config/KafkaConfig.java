package com.kafka.payment.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public static final String PAYMENT_TOPIC = "payment-events";

    @Bean
    public NewTopic inventoryTopic() {
        return new NewTopic(PAYMENT_TOPIC, 1, (short) 1);
    }
}
