package ru.lalibrairiestore.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {

    private String name;

    private String lastname;

    private String EMail;

    private String phoneNumber;

    private String login;

    private String password;

    private String checkPassword;

}
