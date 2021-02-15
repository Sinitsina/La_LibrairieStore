package ru.lalibrairiestore.model.catalog;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "manufacturer")
public class Manufacturer extends GeneralCatalog {

}
