package ru.lalibrairiestore.dto;

import lombok.Data;
import ru.lalibrairiestore.model.Genre;
import ru.lalibrairiestore.transfer.Exist;
import ru.lalibrairiestore.transfer.New;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

@Data
public class BookDTO extends ProductDTO {

    @NotBlank(message = "Please correctly fill out the field", groups = {New.class})
    @Null(groups = {Exist.class})
    private String author;

    @NotBlank(message = "Please correctly fill out the field", groups = {New.class, Exist.class})
    private Genre genres;

    @NotBlank(message = "Please correctly fill out the field", groups = {New.class, Exist.class})
    private short year;

    @NotBlank(message = "Please correctly fill out the field", groups = {New.class, Exist.class})
    @Pattern(regexp = "[0-9]{1,10000}", groups = {New.class, Exist.class})
    private int pages;

    @NotNull(message = "Please correctly fill out the field", groups = {Exist.class, New.class})
    private GeneralCatalogDTO paperType;
}
