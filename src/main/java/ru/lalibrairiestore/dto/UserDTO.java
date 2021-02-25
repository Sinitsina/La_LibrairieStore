package ru.lalibrairiestore.dto;

import lombok.Data;

@Data
public class UserDTO {

    /**
     * User name
     */
    private String name;

    /**
     * User lastname
     */
    private String lastname;

    /**
     * User email
     */
    private String EMail;

    /**
     * User phone number
     */
    private String phoneNumber;

    /**
     * User login
     */
    private String login;

//    public void  userToUserDTO(User user) {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setName(user.getName());
//        userDTO.setLastname(user.getLastname());
//        userDTO.setEMail(user.getEMail());
//        userDTO.setPhoneNumber(user.getPhoneNumber());
//        userDTO.setLogin(user.getLogin());
//
//    }


}
