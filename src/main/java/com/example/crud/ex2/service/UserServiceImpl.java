package com.example.crud.ex2.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crud.ex2.dto.ChatRoomDto;
import com.example.crud.ex2.dto.UserDto;
import com.example.crud.ex2.exception.user.BadRequestException;
import com.example.crud.ex2.exception.user.UserNotFoundException;
import com.example.crud.ex2.model.ChatRoom;
import com.example.crud.ex2.model.User;
import com.example.crud.ex2.repository.MembershipRepository;
import com.example.crud.ex2.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(
        UserRepository userRepository,
        MembershipRepository membershipRepository,
        ModelMapper modelMapper
    ) {
        this.userRepository = userRepository;
        this.membershipRepository = membershipRepository;
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
    public Page<UserDto> getPaginatedUsers(int page, int size) {
        int pageIndex = Math.max(0, page - 1);
        Page<User> userPage = userRepository.findAll(PageRequest.of(pageIndex, size));
        return userPage.map(user -> modelMapper.map(user, UserDto.class));
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

    @Override
    public List<ChatRoomDto> getAllChatRooms(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Cannot find user with ID: " + String.valueOf(id));
        }

        Iterable<ChatRoom> chatRooms = membershipRepository.findChatRoomsByUserId(id);

        return StreamSupport.stream(chatRooms.spliterator(), false)
            .map(chatRoom -> new ChatRoomDto(
                chatRoom.getId(),
                chatRoom.getName(),
                "Last Message Test",
                36,
                chatRoom.getCreatedAt()
            ))
            .collect(Collectors.toList());
    }
}
