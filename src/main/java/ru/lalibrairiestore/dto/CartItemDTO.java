package ru.lalibrairiestore.dto;

import lombok.Data;
import ru.lalibrairiestore.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartItemDTO {

    private Product product;

    private int count;

    private List<CartItemDTO> cartItemDTO;

    private BigDecimal OrderPrice;
}
