package com.example.crud.ex2.dto;

import java.time.LocalDateTime;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChatRoomDto {
    @Nullable
    private Integer id;

    @NotBlank(message="Chat room name must not be empty")
    private String name;

    private String lastMessage;
    private int unreadCount;

    private LocalDateTime createdAt;
}
