package com.example.crud.ex2.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.crud.ex2.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final List<User> users = new ArrayList<>();
    private int nextId = 1;

    @Override
    public User save(User user) {
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getId() == user.getId()) {
                u.setUsername(user.getUsername());
                u.setEmail(user.getEmail());
                u.setUpdatedAt(LocalDate.now());

                return u;
            }
        }

        user.setId(nextId);
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());

        users.add(user);

        nextId += 1;
        return users.getLast();
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<User> targetUser = Optional.empty();
        for (User user : users) {
            if (user.getId() == id) {
                targetUser = Optional.of(user);
                break;
            }
        }

        return targetUser;
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(users); // ignore return reference to real list
    }

    @Override
    public User delete(User user) {
        users.remove(user);

        return user;
    }

    @Override
    public Optional<User> deleteById(int id) {
        Optional<User> user = findById(id);
        if (user.isEmpty()) {
            return Optional.empty();
        }

        delete(user.get());

        return user;
    }
}
