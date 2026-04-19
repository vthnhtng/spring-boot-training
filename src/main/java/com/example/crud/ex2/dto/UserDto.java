package com.example.crud.ex2.dto;

import java.time.LocalDate;

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
    private Integer id;

    @NotBlank(message="Username must not be empty")
    private String username;

    @NotBlank(message="Email must not be empty")
    private String email;

    private LocalDate createdAt;
    private LocalDate updatedAt;
}
