package ru.lalibrairiestore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "db_order", schema = "public")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * User
     */
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    /**
     * Delivery address
     */

    @Embedded
    private Address address;

    /**
     * Customer phone number
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * Order status
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    /**
     * Date of order creation
     */
    @Column(name = "order_date", columnDefinition = "DATE")
    private Date dateOrder;

    /**
     * Order total cost
     */
    @Column(name = "total_cost")
    private BigDecimal totalCost;

    /**
     * Shopping cart
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();
}
