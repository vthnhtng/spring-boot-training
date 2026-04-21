package com.example.crud.ex2.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.ex2.dto.ChatRoomDto;
import com.example.crud.ex2.dto.UserDto;
import com.example.crud.ex2.model.ChatRoom;
import com.example.crud.ex2.repository.MembershipRepository;
import com.example.crud.ex2.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserDto>> getUsers(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Page<UserDto> userPage = userService.getPaginatedUsers(page, size);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Page-Number", String.valueOf(userPage.getNumber()));
        headers.add("X-Page-Size", String.valueOf(userPage.getSize()));
        headers.add("X-Total-Elements", String.valueOf(userPage.getTotalElements()));
        headers.add("X-Total-Pages", String.valueOf(userPage.getTotalPages()));

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(userPage);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") int id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> postUsers(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.create(userDto));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> putUsers(@PathVariable int id,@Valid @RequestBody UserDto userDto) {
        userDto.setId(id);
        return ResponseEntity.ok(userService.update(userDto));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") int id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

    @GetMapping("/users/{id}/rooms")
    public ResponseEntity<List<ChatRoomDto>> getRoom(@PathVariable("id") int id) {
        return ResponseEntity.ok(userService.getAllChatRooms(id));
    }
}
