package ru.lalibrairiestore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book extends Product {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * Book author
     */
    @Column(name = "author")
    private String author;

    /**
     * Book genre
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private Genre genres;

    /**
     * Book publication date
     */
    @Column(name = "year")
    private short year;

    /**
     * Quantity of book pages
     */
    @Column(name = "pages")
    private int pages;
}
