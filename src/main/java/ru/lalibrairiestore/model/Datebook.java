package ru.lalibrairiestore.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Datebook extends Product {

    /**
     * Dated diary or not
     */
    private boolean isDated;
}
