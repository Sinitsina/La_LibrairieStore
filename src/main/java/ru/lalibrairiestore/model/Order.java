package ru.lalibrairiestore.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class Order {

    private User customer;

    /**
     * Delivery address
     */
    private String address;

    /**
     * Customer phone number
     */
    private String phoneNumber;

    /**
     * Order status
     */
    private OrderStatus orderStatus;

    /**
     * Date of order creation
     */
    private Date dateOrder;

    /**
     * Order total cost
     */
    private BigDecimal totalCost;
}
