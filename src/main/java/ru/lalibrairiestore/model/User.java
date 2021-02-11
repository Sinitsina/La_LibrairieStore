package ru.lalibrairiestore.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    /**
     * User name
     */
    private String name;

    /**
     * User lastname
     */
    private String lastname;

    /**
     * User email
     */
    private String EMail;

    /**
     * User phone number
     */
    private String phoneNumber;

    /**
     * User login
     */
    private String login;

    /**
     * User password
     */
    private String password;

    /**
     * User role
     */
    private Role role;

    private boolean isDeleted;



}
