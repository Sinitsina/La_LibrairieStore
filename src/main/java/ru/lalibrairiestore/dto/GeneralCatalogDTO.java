package ru.lalibrairiestore.dto;

import lombok.Data;
import ru.lalibrairiestore.transfer.Exist;
import ru.lalibrairiestore.transfer.New;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class GeneralCatalogDTO {

    @Null(groups = {New.class})
    @NotNull(groups = {Exist.class})
    private Long id;

    @NotBlank(groups = {New.class})
    @Null(groups = {Exist.class})
    private String name;
}
