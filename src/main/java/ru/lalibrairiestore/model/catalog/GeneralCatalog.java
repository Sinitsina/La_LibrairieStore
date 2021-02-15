package ru.lalibrairiestore.model.catalog;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class GeneralCatalog {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     *Name
     */
    @Column(name = "name")
    private String name;

    /**
     * Flag is deleted
     */
    private boolean isDeleted;
}
