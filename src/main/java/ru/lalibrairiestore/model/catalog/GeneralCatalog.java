package ru.lalibrairiestore.model.catalog;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public abstract class GeneralCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Name
     */
    @Column(name = "name")
    private String name;

    /**
     * Flag is deleted
     */
    private boolean isDeleted;
}
