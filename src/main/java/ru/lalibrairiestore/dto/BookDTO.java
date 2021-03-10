package ru.lalibrairiestore.dto;

import lombok.Data;
import ru.lalibrairiestore.model.Genre;

@Data
public class BookDTO extends ProductDTO {

    private String author;

    private Genre genres;

    private short year;

    private int pages;

    private GeneralCatalogDTO paperType;
}
