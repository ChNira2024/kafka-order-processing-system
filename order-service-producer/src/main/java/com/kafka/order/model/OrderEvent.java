package com.kafka.order.model;

import java.time.LocalDateTime;

public record OrderEvent(Long orderId, String status, LocalDateTime timestamp) {}
