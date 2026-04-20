package com.example.crud.ex2.repository;

import com.example.crud.ex2.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{
}
