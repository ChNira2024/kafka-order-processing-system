package com.kafka.notification.model;


import java.time.LocalDateTime;

public record PaymentEvent(
        Long orderId,
        String status,        // e.g., "PAID" or "FAILED"
        LocalDateTime timestamp
) {}
