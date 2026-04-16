package com.example.crud.ex2.api;

import java.util.ArrayList;

import com.example.crud.ex2.dto.CreateUserRequest;
import com.example.crud.ex2.model.User;

public interface UserRepository {
    public ArrayList<User> getList();
    public User create(CreateUserRequest request);
    public User getById(int id) throws Exception;
    public void deleteById(int id) throws Exception;
}
