package com.example.crud.ex2.service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.crud.ex2.api.UserRepository;
import com.example.crud.ex2.dto.CreateUserRequest;
import com.example.crud.ex2.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getList() {
        return users;
    }

    public User create(CreateUserRequest request) {
        int id = users.size() + 1;
        String username = request.getUsername();
        String email = request.getEmail();
        LocalDate createdAt = LocalDate.now();
        LocalDate updatedAt = LocalDate.now();

        User user = new User(id, username, email, createdAt, updatedAt);

        users.add(user);

        return user;
    }

    public User getById(int id) throws Exception {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        throw new Exception(String.format("Cannot find user with ID: %1", String.valueOf(id)));
    }

    public void deleteById(int id) throws Exception {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
                return;
            }
        }

        throw new Exception(String.format("Cannot find user with ID: %1", String.valueOf(id)));
    }
}
