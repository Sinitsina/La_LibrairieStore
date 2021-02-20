package ru.lalibrairiestore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public User findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }
}
