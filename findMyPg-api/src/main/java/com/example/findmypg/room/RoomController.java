package com.example.findmypg.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping
@RestController("/api/findmypg/room")
public class RoomController {
	
	@Autowired 
	private RoomService roomService;
	@PostMapping("/addRooms")
	private Boolean addRooms(@RequestBody RoomDTO dto)
	{
		return roomService.addRoom(dto);
	}

}
