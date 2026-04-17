package com.example.crud.ex2.repository;

import java.util.List;
import java.util.Optional;

import com.example.crud.ex2.model.User;

public interface UserRepository {
    public User save(User user);
    public Optional<User> findById(int id);
    public List<User> findAll();
    public User delete(User user);
    public Optional<User> deleteById(int id);
}
