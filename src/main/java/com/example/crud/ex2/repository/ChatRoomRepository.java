package com.example.crud.ex2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.ex2.model.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
    
}
