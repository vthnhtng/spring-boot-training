package com.example.crud.ex2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.ex2.dto.CreateUserRequest;
import com.example.crud.ex2.dto.UpdateUserRequest;
import com.example.crud.ex2.dto.UserResponse;
import com.example.crud.ex2.model.User;
import com.example.crud.ex2.service.UserService;

@RestController
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public UserResponse getUser(@PathVariable("id") int id) {
        return userService.getById(id);
    }

    @PostMapping("/users")
    public UserResponse postUsers(@RequestBody CreateUserRequest postPayload) {
        return userService.create(postPayload);
    }

    @PatchMapping("/users")
    public UserResponse patchUsers(@RequestBody UpdateUserRequest postPayload) {
        return userService.update(postPayload);
    }

    @DeleteMapping("/users/{id}")
    public UserResponse deleteUser(@PathVariable("id") int id) {
        return userService.deleteById(id);
    }
}
