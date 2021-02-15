package ru.lalibrairiestore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Address {

    /**
     * Region
     */
    private String region;

    /**
     * City
     */
    private String city;

    /**
     * District
     */
    private String district;

    /**
     * Street
     */
    private String street;

    /**
     * House
     */
    private String house;

    /**
     * Apartment
     */
    private String apartment;

    /**
     * Index
     */
    private String index;
}
