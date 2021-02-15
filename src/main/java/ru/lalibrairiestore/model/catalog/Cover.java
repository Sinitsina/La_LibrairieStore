package ru.lalibrairiestore.model.catalog;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "cover")
public class Cover extends GeneralCatalog {

}
