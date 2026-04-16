package com.example.crud.ex2.repository;

import java.util.List;
import java.util.Optional;

import com.example.crud.ex2.model.User;

public interface UserRepository {
    public void save(User user);
    public Optional<User> findById(int id);
    public List<User> findAll();
    public boolean deleteById(int id);
    public boolean existsById(int id);
}
