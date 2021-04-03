package ru.lalibrairiestore.model;

import lombok.Data;

import javax.persistence.Embeddable;


@Data
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
    private Integer house;

    /**
     * Apartment
     */
    private Integer apartment;

    /**
     * Index
     */
    private Integer index;
}
