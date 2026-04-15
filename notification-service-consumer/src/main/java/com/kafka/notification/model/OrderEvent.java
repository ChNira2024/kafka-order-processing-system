package com.kafka.notification.model;

import java.time.LocalDateTime;

import com.kafka.notification.enums.OrderStatus;

public record OrderEvent(
        Long orderId,
        OrderStatus status,
        String productId,
        Integer quantity,
        Double amount,
        LocalDateTime timestamp
) {}