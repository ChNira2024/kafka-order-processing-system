package com.kafka.inventory.model;

import java.time.LocalDateTime;

import com.kafka.inventory.enums.OrderStatus;

public record OrderEvent(Long orderId, String productId, Integer quantity, Double amount, OrderStatus status, LocalDateTime timestamp) {}
