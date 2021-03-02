package ru.lalibrairiestore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegistrationDTO {

    @NotBlank(message = "Name may not be blank")
    private String name;

    @NotBlank(message = "Lastname may not be blank")
    private String lastname;

    @NotBlank(message = "Email may not be blank")
    @Email(message = "Email may not be blank")
    private String EMail;

    @NotBlank(message = "Phone number may not be blank")
    private String phoneNumber;

    @NotBlank(message = "Login may not be blank")
    private String login;

    @NotBlank(message = "Password may not be blank")
    private String password;

    @NotBlank(message = "Password may not be blank")
    private String checkPassword;

}
