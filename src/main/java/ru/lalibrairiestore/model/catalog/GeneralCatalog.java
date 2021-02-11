package ru.lalibrairiestore.model.catalog;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GeneralCatalog {
    private String name;
    private boolean isDeleted;
}
