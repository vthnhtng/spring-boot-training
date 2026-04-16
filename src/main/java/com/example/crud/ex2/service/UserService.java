package com.example.crud.ex2.service;

import java.util.List;

import com.example.crud.ex2.dto.CreateUserRequest;
import com.example.crud.ex2.dto.UpdateUserRequest;
import com.example.crud.ex2.dto.UserResponse;
import com.example.crud.ex2.model.User;

public interface UserService {
    public List<User> getAll();
    public UserResponse create(CreateUserRequest request);
    public UserResponse update(UpdateUserRequest request);
    public UserResponse getById(int id);
    public UserResponse deleteById(int id);
}
