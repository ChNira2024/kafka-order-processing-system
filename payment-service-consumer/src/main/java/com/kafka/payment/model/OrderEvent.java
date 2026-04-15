package com.kafka.payment.model;

import java.time.LocalDateTime;

import com.kafka.payment.enums.OrderStatus;

public record OrderEvent(
        Long orderId,
        OrderStatus status,
        String productId,
        Integer quantity,
        Double amount,
        LocalDateTime timestamp
) {}