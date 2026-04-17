package com.example.crud.ex2.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.crud.ex2.dto.UserDto;
import com.example.crud.ex2.exception.user.UserNotFoundException;
import com.example.crud.ex2.model.User;
import com.example.crud.ex2.repository.UserRepository;

import org.modelmapper.ModelMapper;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(
            UserRepository userRepository,
            ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();

        return users
            .stream()
            .map(user -> modelMapper.map(user, UserDto.class))
            .collect(Collectors.toList());

    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto update(UserDto userDto) {
        int id = userDto.getId();
        if (id <= 0) {
            throw new UserNotFoundException("Invalid request ID: " + String.valueOf(id));
        }

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Cannot find user with ID: " + String.valueOf(id));
        }

        User updatedUser = userRepository.save(modelMapper.map(userDto, User.class));

        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Cannot find user with ID: " + String.valueOf(id));
        }

        return modelMapper.map(user.get(), UserDto.class);
    }

    @Override
    public UserDto deleteById(int id) {
        Optional<User> deletedUser = userRepository.deleteById(id);
        if (deletedUser.isEmpty()) {
            throw new UserNotFoundException("Cannot find user with ID: " + String.valueOf(id));
        }

        return modelMapper.map(deletedUser.get(), UserDto.class);
    }
}
