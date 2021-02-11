package ru.lalibrairiestore.model;

public enum OrderStatus {
    CREATED, AWAITING_PAYMENT, CONFIRMED, AWAITING_SHIPMENT,
    SHIPPED, AWAITING_PICKUP, COMPLETED, CANCELLED
}
