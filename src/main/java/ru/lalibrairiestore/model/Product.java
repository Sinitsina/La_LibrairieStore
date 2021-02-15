package ru.lalibrairiestore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.lalibrairiestore.model.catalog.Cover;
import ru.lalibrairiestore.model.catalog.Format;
import ru.lalibrairiestore.model.catalog.Manufacturer;
import ru.lalibrairiestore.model.catalog.SheetsType;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * Product title
     */
    @Column(name = "title")
    private String title;

    /**
     * Product description
     */
    @Column(name = "description")
    private String description;

    /**
     * Current percentage discount
     */
    @Column(name = "discount_percent")
    private byte discountPercent;

    /**
     * Product price
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * Product type of cover
     */
    @ManyToOne
    @Column(name = "cover_id")
    private Cover cover;

    /**
     * Product format
     */
    @ManyToOne
    @Column(name = "format_id")
    private Format format;

    /**
     * Product sheet's type
     */
    @ManyToOne
    @Column(name = "sheets_type_id")
    private SheetsType sheetsType;

    /**
     * Product manufacturer
     */
    @ManyToOne
    @Column(name = "manufacturer_id")
    private Manufacturer manufacturer;

    /**
     * Flag is deleted
     */
    @Column(name = "is_deleted")
    private boolean isDeleted;

}
