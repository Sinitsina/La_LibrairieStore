package ru.lalibrairiestore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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
}
