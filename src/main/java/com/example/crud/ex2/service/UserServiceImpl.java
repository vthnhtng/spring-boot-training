package com.example.crud.ex2.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.crud.ex2.dto.UserDto;
import com.example.crud.ex2.exception.user.BadRequestException;
import com.example.crud.ex2.exception.user.UserNotFoundException;
import com.example.crud.ex2.model.User;
import com.example.crud.ex2.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(
        UserRepository userRepository,
        ModelMapper modelMapper
    ) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> getAll() {
        Iterable<User> users = userRepository.findAll();

        return StreamSupport.stream(users.spliterator(), false)
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto create(UserDto userDto) {
        userDto.setId(null);
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setUpdatedAt(LocalDateTime.now());

        User user = modelMapper.map(userDto, User.class);

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto update(UserDto userDto) {
        int id = userDto.getId();
        if (id <= 0) {
            throw new BadRequestException("Invalid request ID: " + String.valueOf(id));
        }

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Cannot find user with ID: " + String.valueOf(id));
        }

        userDto.setUpdatedAt(user.get().getCreatedAt());
        userDto.setUpdatedAt(LocalDateTime.now());
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
        Optional<User> toDeteleUser = userRepository.findById(id);
        if (toDeteleUser.isEmpty()) {
            throw new UserNotFoundException("Cannot find user with ID: " + String.valueOf(id));
        }

        userRepository.delete(toDeteleUser.get());

        return modelMapper.map(toDeteleUser.get(), UserDto.class);
    }
}
