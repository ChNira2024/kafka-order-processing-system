package com.kafka.order.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String status; // PENDING, CONFIRMED, FAILED, SHIPPED, DELIVERED
    private Double totalAmount;
    private LocalDateTime createdAt = LocalDateTime.now();
    
    
}
