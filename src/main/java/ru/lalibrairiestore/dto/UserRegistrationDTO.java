package ru.lalibrairiestore.dto;

import lombok.Data;
import ru.lalibrairiestore.transfer.New;
import ru.lalibrairiestore.transfer.UpdatePassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationDTO {

    @Null
    private long id;

    @NotBlank(message = "Please correctly fill out the field", groups = {New.class})
    @Null(groups = {UpdatePassword.class})
    private String name;

    @NotBlank(message = "Please correctly fill out the field", groups = {New.class})
    @Null(groups = {UpdatePassword.class})
    private String lastname;

    @NotBlank(message = "Please correctly fill out the field", groups = {New.class})
    @Email
    @Null(groups = {UpdatePassword.class})
    private String email;

    @NotBlank(message = "Please correctly fill out the field", groups = {New.class})
    @Null(groups = {UpdatePassword.class})
    private String phoneNumber;

    @NotBlank(message = "Please correctly fill out the field", groups = {New.class})
    @Null(groups = {UpdatePassword.class})
    private String login;

    @NotBlank(groups = {New.class, UpdatePassword.class})
    @Size(message = "Password length must be at least 5 and no more than 15 characters", min = 5, max = 15)
    private String password;

    @NotBlank(groups = {New.class, UpdatePassword.class})
    @Size(message = "Password length must be at least 5 and no more than 15 characters", min = 5, max = 15)
    private String checkPassword;

}
