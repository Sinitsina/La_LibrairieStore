package ru.lalibrairiestore.dto;

import lombok.Data;
import ru.lalibrairiestore.transfer.Exist;
import ru.lalibrairiestore.transfer.New;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class ProductDTO {

    @Null(groups = {New.class, Exist.class})
    private Long id;

    @NotBlank(message = "Please correctly fill out the field", groups = {New.class})
    @Null(groups = {Exist.class})
    private String title;

    @NotBlank(message = "Please correctly fill out the field", groups = {New.class, Exist.class})
    private String description;

    @NotNull(message = "Please correctly fill out the field", groups = {New.class, Exist.class})
    @Pattern(message = "The discount must be from 1 to 99 percent", regexp = "[0-9]{1,99}")
    private byte discountPercent;

    @NotNull(message = "Please correctly fill out the field", groups = {New.class, Exist.class})
    @DecimalMin(message = "The price must be at least 1 rub.", value = "1.0", groups = {New.class, Exist.class})
    @Pattern(regexp = "[0-9]{1,100000}")
    private BigDecimal price;

    @NotBlank(message = "Please correctly fill out the field", groups = {New.class, Exist.class})
    private GeneralCatalogDTO manufacturer;

    @NotNull(message = "Please correctly fill out the field", groups = {New.class, Exist.class})
    private GeneralCatalogDTO cover;

    @NotNull(message = "Please correctly fill out the field", groups = {New.class, Exist.class})
    private GeneralCatalogDTO format;

    @NotNull(message = "Please correctly fill out the field", groups = {New.class, Exist.class})
    private GeneralCatalogDTO sheetsType;

}
