package com.example.findmypg.room;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.findmypg.entities.Room;

public interface RoomRepositry extends JpaRepository<Room, Long> {

}
