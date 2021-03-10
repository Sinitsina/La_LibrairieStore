package ru.lalibrairiestore.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

//    private Long id;

    private String title;

    private String description;

    private byte discountPercent;

    private BigDecimal price;

    private GeneralCatalogDTO cover;

    private GeneralCatalogDTO format;

    private GeneralCatalogDTO sheetsType;

}
