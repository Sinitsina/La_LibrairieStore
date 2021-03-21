package ru.lalibrairiestore.dto;

import lombok.Data;
import ru.lalibrairiestore.model.Address;
import ru.lalibrairiestore.model.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDTO {

    private Long id;

    private Address address;

    private String phoneNumber;

    private Date dateOrder;

    private BigDecimal totalCost;

    private OrderStatus orderStatus;

    private UserDTO userDTO;


}
