package com.kafka.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.inventory.entity.InventoryDLQ;
import com.kafka.inventory.model.OrderEvent;
import com.kafka.inventory.repository.InventoryDLQRepository;

@Service
public class InventoryDLQConsumer {
	@Autowired
	private InventoryDLQRepository inventoryDLQRepository;

    @KafkaListener(topics = "order-topic.DLT", groupId = "inventory-dlq-group")
    public void handleDLQ(OrderEvent event) {

        System.out.println("🚨 DLQ received: " + event.orderId());

        // save to DB
        inventoryDLQRepository.save(new InventoryDLQ(
                event.orderId(),
                "Failed after retries",
                event.status().name()
        ));
    }
}