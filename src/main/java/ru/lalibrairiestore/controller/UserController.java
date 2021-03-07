package ru.lalibrairiestore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.lalibrairiestore.dto.UserDTO;
import ru.lalibrairiestore.dto.UserRegistrationDTO;
import ru.lalibrairiestore.model.User;
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

    @GetMapping("/all")
    @ApiOperation("find all users method")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public UserDTO findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/registration")
    public UserDTO registration(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        return userService.registration(userRegistrationDTO);
    }
}
