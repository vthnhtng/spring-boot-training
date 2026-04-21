package com.example.crud.ex2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.crud.ex2.model.ChatRoom;
import com.example.crud.ex2.model.Membership;
import com.example.crud.ex2.model.MembershipId;

public interface MembershipRepository extends JpaRepository<Membership, MembershipId> {
    @Query("""
        SELECT m.chatRoom
        FROM Membership m
        WHERE m.user.id = :userId
    """)
    public Iterable<ChatRoom> findChatRoomsByUserId(Integer userId);
}
