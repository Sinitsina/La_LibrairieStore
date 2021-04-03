package ru.lalibrairiestore.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartItemListDTO {

    private List<CartItemDTO> cartItemDTOS;

    private BigDecimal OrderPrice;
}
