package ru.lalibrairiestore.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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
    private int house;

    /**
     * Apartment
     */
    private int apartment;

    /**
     * Index
     */
    private int index;

}
