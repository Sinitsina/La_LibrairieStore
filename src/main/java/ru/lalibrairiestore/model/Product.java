package ru.lalibrairiestore.model;

import lombok.Getter;
import lombok.Setter;
import ru.lalibrairiestore.model.catalog.Cover;
import ru.lalibrairiestore.model.catalog.Format;
import ru.lalibrairiestore.model.catalog.Manufacturer;
import ru.lalibrairiestore.model.catalog.SheetsType;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {

    /**
     * Product title
     */
    private String title;

    /**
     * Product description
     */
    private String description;

    /**
     * Current percentage discount
     */
    private byte discountPercent;

    /**
     * Product price
     */
    private BigDecimal price;

    /**
     * Product type of cover
     */
    private Cover cover;

    /**
     * Product format
     */
    private Format format;

    /**
     * Product sheet's type
     */
    private SheetsType sheetType;

    /**
     * Product manufacturer
     */
    private Manufacturer manufacturer;

    private boolean isDeleted;

}
