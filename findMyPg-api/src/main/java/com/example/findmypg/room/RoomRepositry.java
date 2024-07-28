package com.example.findmypg.room;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.findmypg.entities.Room;

public interface RoomRepositry extends JpaRepository<Room, Long> {


//	List<Room> findByFloorId(Long roomId);

	List<Room> findByFloorId_Id(Long floorId);

	Room findByFloorId_IdAndRoomNumber(Long id, int roomNumber);

}
