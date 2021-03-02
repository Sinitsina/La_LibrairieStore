package ru.lalibrairiestore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "db_order")
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
    private User customer;

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
    @Column(name = "order_date")
    private Date dateOrder;

    /**
     * Order total cost
     */
    @Column(name = "total_cost")
    private BigDecimal totalCost;
}
