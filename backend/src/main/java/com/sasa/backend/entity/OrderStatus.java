package com.sasa.backend.entity;

public enum OrderStatus {
    IN_PROGRESS,
    COMPLETED,
    PENDING,
    ERROR,
    CANCELLED,    // Additional status to consider
    SHIPPED       // Status when the order is on its way to the customer
}
