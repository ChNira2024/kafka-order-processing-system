package com.kafka.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "inventory_dlq")
@Data
public class InventoryDLQ {

    @Id
    private Long orderId;

    private String reason;
    private String status;

    public InventoryDLQ() {}

    public InventoryDLQ(Long orderId, String reason, String status) {
        this.orderId = orderId;
        this.reason = reason;
        this.status = status;
    }

    // getters & setters
}
