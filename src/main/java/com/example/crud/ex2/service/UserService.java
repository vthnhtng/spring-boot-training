package com.example.crud.ex2.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.crud.ex2.dto.ChatRoomDto;
import com.example.crud.ex2.dto.UserDto;

public interface UserService {
    public List<UserDto> getAll();
    public Page<UserDto> getPaginatedUsers(int page, int size);
    public UserDto create(UserDto userDto);
    public UserDto update(UserDto userDto);
    public UserDto getById(int id);
    public UserDto deleteById(int id);
    public List<ChatRoomDto> getAllChatRooms(int id);
}
