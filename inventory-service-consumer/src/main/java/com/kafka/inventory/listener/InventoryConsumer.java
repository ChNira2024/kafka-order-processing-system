package com.kafka.inventory.listener;
import org.springframework.kafka.annotation.BackOff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.kafka.inventory.entity.Inventory;
import com.kafka.inventory.enums.OrderStatus;
import com.kafka.inventory.model.OrderEvent;
import com.kafka.inventory.repository.InventoryRepository;
@Service
public class InventoryConsumer {

    @Autowired
    private InventoryRepository repo;

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @RetryableTopic(
            attempts = "3",
            backOff = @BackOff(delay = 5000) // Changed to @BackOff
    )
    @KafkaListener(topics = "order-topic-producer", groupId = "inventory-group")
    public void consume(OrderEvent event, Acknowledgment ack) {

        System.out.println("📥 Processing Order: " + event.orderId());

        // ✅ Idempotent check
        if (repo.existsById(event.orderId())) {
            ack.acknowledge();
            return;
        }

        // 🔹 Business Logic
        boolean stockAvailable = checkStock(event);

        if (!stockAvailable) {
            throw new RuntimeException("Stock not available");
        }

        // ✅ Save inventory
        repo.save(new Inventory(
                event.orderId(),
                event.productId(),
                event.quantity(),
                "RESERVED"
        ));

        // 🔥 IMPORTANT FIX (record → new object)
        OrderEvent updatedEvent = new OrderEvent(
                event.orderId(),
                event.productId(),
                event.quantity(),
                event.amount(),
                OrderStatus.INVENTORY_DONE,
                event.timestamp()
        );

        kafkaTemplate.send(
                "inventory-topic",
                String.valueOf(event.orderId()),
                updatedEvent
        );

        ack.acknowledge();
    }

    private boolean checkStock(OrderEvent event) {
        return true;
    }
}