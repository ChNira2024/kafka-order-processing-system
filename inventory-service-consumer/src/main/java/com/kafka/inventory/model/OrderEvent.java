package com.kafka.inventory.model;

import java.time.LocalDateTime;

public record OrderEvent(Long orderId, String status, LocalDateTime timestamp) {}
