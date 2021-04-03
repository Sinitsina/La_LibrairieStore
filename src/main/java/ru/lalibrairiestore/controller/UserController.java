package ru.lalibrairiestore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.lalibrairiestore.dto.AuthenticationRequestDTO;
import ru.lalibrairiestore.dto.UserDTO;
import ru.lalibrairiestore.dto.UserRegistrationDTO;
import ru.lalibrairiestore.service.UserService;
import ru.lalibrairiestore.transfer.New;
import ru.lalibrairiestore.transfer.UpdatePassword;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Map;

import static ru.lalibrairiestore.model.Role.ROLE_ADMIN;
import static ru.lalibrairiestore.model.Role.ROLE_CUSTOMER;

@RestController
@RequestMapping("/api/users")
@Api(value = "User", tags = {"User"})
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/registration")
    @ApiOperation("user registration")
    public UserDTO registration(@Validated(New.class) @RequestBody UserRegistrationDTO userRegistrationDTO) {

        return userService.registration(userRegistrationDTO);
    }

    @PostMapping("/login")
    @ApiOperation("user authorization")
    public ResponseEntity<Map<String, String>> authorization(@RequestBody AuthenticationRequestDTO requestDTO) {

        return userService.authorization(requestDTO);
    }

    @RolesAllowed({ROLE_ADMIN})
    @GetMapping("/user-all")
    @ApiOperation("find all users")
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @RolesAllowed({ROLE_ADMIN})
    @GetMapping("/id/{userId}")
    @ApiOperation("find user by id")
    public UserDTO findUserById(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }

    @RolesAllowed({ROLE_ADMIN})
    @GetMapping("/login/{login}")
    @ApiOperation("find user by login")
    public UserDTO findUserByLogin(@PathVariable String login) {
        return userService.findByLogin(login);
    }

    @RolesAllowed({ROLE_ADMIN})
    @GetMapping("/email/{email}")
    @ApiOperation("find user by email")
    public UserDTO findUserByEMail(@PathVariable String email) {
        return userService.findUserByEMail(email);
    }

    @RolesAllowed({ROLE_ADMIN})
    @GetMapping("/phone/{phone}")
    @ApiOperation("find user by login")
    public UserDTO findUserByPhoneNumber(@PathVariable String phone) {
        return userService.findUserByPhoneNumber(phone);
    }

    @RolesAllowed({ROLE_CUSTOMER})
    @PutMapping({"/change-pass"})
    @ApiOperation("change user password")
    public Long changePassword(@Validated(UpdatePassword.class) UserRegistrationDTO userRegistrationDTO) {
        return userService.changePassword(userRegistrationDTO);
    }
}
