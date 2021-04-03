package ru.lalibrairiestore.dto;

import lombok.Data;
import ru.lalibrairiestore.model.Address;

import javax.validation.constraints.NotNull;

@Data
public class OrderDetailsDTO {

    @NotNull(message = "Please correctly fill out the field")
    private String phoneNumber;

    @NotNull(message = "Please correctly fill out the field")
    private Address address;
}
