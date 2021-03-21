package ru.lalibrairiestore.model.catalog;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "sheets_type")
public class SheetsType extends GeneralCatalog {

}
