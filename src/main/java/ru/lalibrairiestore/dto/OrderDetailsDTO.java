package ru.lalibrairiestore.dto;

import lombok.Data;
import ru.lalibrairiestore.model.Address;

@Data
public class OrderDetailsDTO {

    private String phoneNumber;

    private Address address;
}
