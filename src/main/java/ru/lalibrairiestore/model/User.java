package ru.lalibrairiestore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "db_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * User name
     */
    @Column(name = "name")
    private String name;

    /**
     * User lastname
     */
    @Column(name = "lastname")
    private String lastname;

    /**
     * User email
     */
    @Column(name = "email")
    private String EMail;

    /**
     * User phone number
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * User login
     */
    @Column(name = "login")
    private String login;

    /**
     * User password
     */
    @Column(name = "password")
    private String password;

    /**
     * User role
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    /**
     * Flag is deleted
     */
    @Column(name = "is_deleted")
    private boolean isDeleted;

    /**
     * List of favourite products
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "favourites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> favouriteProducts = new ArrayList<>();
}
