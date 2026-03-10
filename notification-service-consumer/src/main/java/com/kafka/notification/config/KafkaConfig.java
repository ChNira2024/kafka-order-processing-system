package com.kafka.notification.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public static final String TOPIC = "notification-events";

    @Bean
    public NewTopic paymentTopic() {
        return new NewTopic(TOPIC, 1, (short)1);
    }
}
