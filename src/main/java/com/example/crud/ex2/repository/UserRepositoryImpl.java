package com.example.crud.ex2.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.crud.ex2.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
    final List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        for (User u : users) {
            if (u.getId() == user.getId()) {
                u.setId(user.getId());
                u.setUsername(user.getUsername());
                u.setEmail(user.getEmail());
                u.setCreatedAt(user.getCreatedAt());
                u.setCreatedAt(LocalDate.now());

                return;
            }
        }

        user.setId(users.size() + 1);
        users.add(user);
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
        return users;
    }

    @Override
    public boolean deleteById(int id) {
        for (int i = 0; i <  users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean existsById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return true;
            }
        }

        return false;
    }
}
