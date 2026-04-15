package com.kafka.payment.enums;

public enum OrderStatus {
    CREATED,
    INVENTORY_DONE,
    INVENTORY_FAILED,
    PAYMENT_SUCCESS,
    PAYMENT_FAILED,
    COMPLETED,
    CANCELLED
}