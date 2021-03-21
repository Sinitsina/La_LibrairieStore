package ru.lalibrairiestore.model.catalog;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "cover")
public class Cover extends GeneralCatalog {

}
