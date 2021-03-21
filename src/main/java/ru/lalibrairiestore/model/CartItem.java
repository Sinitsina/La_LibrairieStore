package ru.lalibrairiestore.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shopping_card")
public class CartItem {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class CartItemId implements Serializable {

        private Long product_id;

        private Long order_id;
    }

    @EmbeddedId
    private CartItemId cartItemId;

    /**
     * Product
     */
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id",
            nullable = false, insertable = false, updatable = false)
    private Product product;

    /**
     * Order
     */
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id",
            nullable = false, insertable = false, updatable = false)
    private Order order;

    /**
     * Quantity of items in shopping card
     */
    @Column(name = "count")
    private int count;
}