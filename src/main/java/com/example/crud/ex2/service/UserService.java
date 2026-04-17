package com.example.crud.ex2.service;

import java.util.List;

import com.example.crud.ex2.dto.UserDto;

public interface UserService {
    public List<UserDto> getAll();
    public UserDto create(UserDto userDto);
    public UserDto update(UserDto userDto);
    public UserDto getById(int id);
    public UserDto deleteById(int id);
}
