package com.kafka.inventory.listener;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kafka.inventory.config.KafkaConfig;
import com.kafka.inventory.model.InventoryEvent;
import com.kafka.inventory.model.OrderEvent;
import com.kafka.inventory.repository.InventoryRepository;

@Component
public class InventoryController {

	@Autowired
	private InventoryRepository inventoryRepo;

	@Autowired
	private KafkaTemplate<String, InventoryEvent> kafkaTemplate;

	@KafkaListener(topics = "order-events", groupId = "inventory-group")
	public void consumeOrderEvent(OrderEvent orderEvent) {

		System.out.println("Inventory received: " + orderEvent);

		// assume stock available
		InventoryEvent inventoryEvent = new InventoryEvent(orderEvent.orderId(), "CONFIRMED", LocalDateTime.now());

		kafkaTemplate.send(KafkaConfig.INVENTORY_TOPIC, inventoryEvent);

		System.out.println("Inventory event sent: " + inventoryEvent);

	}
}
