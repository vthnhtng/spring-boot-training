package com.example.crud.ex2.dto;

public class UpdateUserRequest {
    private int id;
    private String username;
    private String email;;

    public UpdateUserRequest(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public int  getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UpdateUserRequest() {
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
