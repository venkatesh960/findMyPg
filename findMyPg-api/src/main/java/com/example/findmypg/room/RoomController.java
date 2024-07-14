package com.example.findmypg.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		System.out.println(dto.getSelectedBuilding());
	    System.out.println(dto.getRoomNumber());
	    System.out.println(dto.getShares());
	    System.out.println(dto.getRates());
	    System.out.println(dto.getFloorRooms());

	    // Process the rooms
	    

		return roomService.addRoom(dto);
	}
	
	@GetMapping("/getListofRooms")
	private List<RoomDTO> getListOfRomms(@RequestParam Long floorId)
	{
		return roomService.getListOfRooms(floorId);
	}	
	
}
