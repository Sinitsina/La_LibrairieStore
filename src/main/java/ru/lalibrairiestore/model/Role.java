package ru.lalibrairiestore.model;

public enum Role {
    ADMIN, CUSTOMER;

    /**
     * Fields below are needed for @RolesAllowed(Spring Security)
     */
    public static final String ROLE_CUSTOMER = "CUSTOMER";

    public static final String ROLE_ADMIN = "ADMIN";
}
