package com.example.crud.ex2.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.ex2.api.UserRepository;
import com.example.crud.ex2.dto.CreateUserRequest;
import com.example.crud.ex2.model.User;

@RestController
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public ArrayList<User> getUsers() {
        return userRepository.getList();
    }

    @PostMapping("/users")
    public void postUsers(@RequestBody CreateUserRequest postPayload) {
        userRepository.create(postPayload);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") int id) {
        try {
            return userRepository.getById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            
        }
    }
}
