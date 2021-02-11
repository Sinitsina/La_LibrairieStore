package ru.lalibrairiestore.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book extends Product {

    /**
     * Book author
     */
    private String author;

    /**
     * Book genre
     */
    private Genre genre;

    /**
     * Book publication date
     */
    private short year;

    /**
     * Quantity of book pages
     */
    private short pages;
}
