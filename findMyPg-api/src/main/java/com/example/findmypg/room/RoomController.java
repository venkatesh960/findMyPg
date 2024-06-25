package com.example.findmypg.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.findmypg.entities.Room;
@RestController
@RequestMapping("/api/findmypg/room")
public class RoomController {
	
	@Autowired 
	private RoomService roomService;
	@PostMapping("/addRooms")
	private Room addRooms(@RequestBody RoomDTO dto)
	{
		return roomService.addRoom(dto);
	}

}
