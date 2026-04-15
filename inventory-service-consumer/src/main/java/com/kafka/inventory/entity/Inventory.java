package com.kafka.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "inventory")
@Data
public class Inventory {

    @Id
    private Long orderId;  // ✅ idempotency

    private String productId;
    private Integer quantity;
    private String status; // RESERVED / FAILED

    public Inventory() {}

    public Inventory(Long orderId, String productId, Integer quantity, String status) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
    }

    // getters & setters
}