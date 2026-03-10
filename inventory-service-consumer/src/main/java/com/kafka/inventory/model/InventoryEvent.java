package com.kafka.inventory.model;

import java.time.LocalDateTime;

public record InventoryEvent(Long orderId, String status, LocalDateTime timestamp) {}
