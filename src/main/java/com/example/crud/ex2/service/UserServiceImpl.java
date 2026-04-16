package com.example.crud.ex2.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.crud.ex2.dto.CreateUserRequest;
import com.example.crud.ex2.dto.UpdateUserRequest;
import com.example.crud.ex2.dto.UserResponse;
import com.example.crud.ex2.model.User;
import com.example.crud.ex2.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse create(CreateUserRequest request) {
        String username = request.getUsername();
        String email = request.getEmail();
        LocalDate createdAt = LocalDate.now();
        LocalDate updatedAt = LocalDate.now();

        User newUser = new User(username, email, createdAt, updatedAt);

        userRepository.save(newUser);

        return new UserResponse(
            true,
            String.format("New user with email %s is Successfully created!", email),
            newUser
        );
    }

    @Override
    public UserResponse update(UpdateUserRequest request) {
        int id = request.getId();

        Optional<User> user = userRepository.findById(id);

        Boolean success = true;
        String message = "User successfully updated!";
        if (user.isEmpty()) {
            success = false;
            message = "Failed to update user!";

            return new UserResponse(success, message, null);
        }
        
        User userObj = user.get();
        userObj.setEmail(request.getEmail());
        userObj.setUsername(request.getUsername());
        userObj.setUpdatedAt(LocalDate.now());

        userRepository.save(user.get());

        return new UserResponse(success, message, userObj);
    }

    @Override
    public UserResponse getById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return new UserResponse(
                false,
                "Could not find user with ID: " + String.valueOf(id),
                null
            );
        }

        return new UserResponse(
            true,
            "Successfully get user by ID: " + String.valueOf(id),
            user.get()
        );
    }

    @Override
    public UserResponse deleteById(int id){
        if (!userRepository.deleteById(id)) {
            return new UserResponse(
                false,
                "Failed to delete user by ID: " + String.valueOf(id),
                null
            );
        }

        return new UserResponse(
            true,
            "Successfully delete user by ID: " + String.valueOf(id),
            null
        );
    }
}
