package com.kafka.payment.model;

import java.time.LocalDateTime;

public record InventoryEvent(Long orderId, String status, LocalDateTime timestamp) {}
