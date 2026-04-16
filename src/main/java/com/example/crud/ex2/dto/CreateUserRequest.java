package com.example.crud.ex2.dto;

public class CreateUserRequest {
    private String username;
    private String email;

    public CreateUserRequest(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public CreateUserRequest() {
        username = "";
        email = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
