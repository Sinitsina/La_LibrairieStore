package ru.lalibrairiestore.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lalibrairiestore.model.catalog.Cover;
import ru.lalibrairiestore.model.catalog.Format;
import ru.lalibrairiestore.model.catalog.Manufacturer;
import ru.lalibrairiestore.model.catalog.SheetsType;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private int discountPercent;

    /**
     * Product price
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * Product type of cover
     */
    @ManyToOne
    @JoinColumn(name = "cover_id")
    private Cover cover;

    /**
     * Product format
     */
    @ManyToOne
    @JoinColumn(name = "format_id")
    private Format format;

    /**
     * Product sheet's type
     */
    @ManyToOne
    @JoinColumn(name = "sheets_type_id")
    private SheetsType sheetsType;

    /**
     * Product sheet's type
     */
    @ManyToOne
    @JoinColumn(name = "paper_type_id")
    private SheetsType paperType;

    /**
     * Product manufacturer
     */
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    /**
     * Flag is deleted
     */
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
