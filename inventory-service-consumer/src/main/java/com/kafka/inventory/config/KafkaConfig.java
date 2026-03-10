package com.kafka.inventory.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

	public static final String INVENTORY_TOPIC = "inventory-events";

    @Bean
    public NewTopic inventoryTopic() {
        return new NewTopic(INVENTORY_TOPIC, 1, (short) 1);
    }
}
