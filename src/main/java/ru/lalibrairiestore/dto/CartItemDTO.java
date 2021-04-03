package ru.lalibrairiestore.dto;

import lombok.Data;
import ru.lalibrairiestore.model.Product;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class CartItemDTO {

    public interface Count {

    }

    private Product product;

    @Min(value = 1, groups = {Count.class})
    @Max(value = 50, groups = {Count.class})
    private int count;

}
