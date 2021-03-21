package ru.lalibrairiestore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.lalibrairiestore.dto.UserDTO;
import ru.lalibrairiestore.dto.UserRegistrationDTO;
import ru.lalibrairiestore.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api("User controller")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    @ApiOperation("user registration")
    public UserDTO registration(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        return userService.registration(userRegistrationDTO);
    }

    @GetMapping("/user-all")
    @ApiOperation("find all users")
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    @ApiOperation("find user by id")
    public UserDTO findUserById(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }

    @GetMapping("/{login}")
    @ApiOperation("find user by login")
    public UserDTO findUserByLogin(@PathVariable String login) {
        return userService.findUserByLogin(login);
    }

    @GetMapping("/{email}")
    @ApiOperation("find user by email")
    public UserDTO findUserByEMail(@PathVariable String email) {
        return userService.findUserByEMail(email);
    }

    @GetMapping("/{phone}")
    @ApiOperation("find user by login")
    public UserDTO findUserByPhoneNumber(@PathVariable String phone) {
        return userService.findUserByPhoneNumber(phone);
    }
}
