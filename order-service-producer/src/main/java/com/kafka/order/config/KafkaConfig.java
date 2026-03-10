package com.kafka.order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

//    public static final String TOPIC = "order-tracking-events";
//
//    @Bean
//    public NewTopic orderTopic() {
//        return new NewTopic(TOPIC, 1, (short) 1);
//    }
//
//    @Bean
//    public ProducerFactory<String, OrderEvent> producerFactory() {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        return new DefaultKafkaProducerFactory<>(config);
//    }
//
//    @Bean
//    public KafkaTemplate<String, OrderEvent> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
	
	public static final String ORDER_TOPIC = "order-events";

    @Bean
    public NewTopic orderTopic() {
        return new NewTopic(ORDER_TOPIC, 1, (short) 1);
    }
}
