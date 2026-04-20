package com.example.crud.ex2.dto;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    @Nullable
    private Integer id;

    @NotBlank(message="Username must not be empty")
    private String username;

    @NotBlank(message="Email must not be empty")
    @Email
    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
