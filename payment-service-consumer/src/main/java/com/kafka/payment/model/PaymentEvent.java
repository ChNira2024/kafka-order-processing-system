package com.kafka.payment.model;


import java.time.LocalDateTime;

public record PaymentEvent(Long orderId, String status, LocalDateTime timestamp) {}
