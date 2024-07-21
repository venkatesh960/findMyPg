package com.example.findmypg.floor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.findmypg.entities.Floor;


@RestController
@RequestMapping("/api/findmypg/floor")
public class FloorController {
	
	@Autowired 
	private FloorService floorService;
	
	@PostMapping("/addFloor")
	private Floor addFloor(@RequestBody FloorDTO floorDTO)
	{
		
		return floorService.addFloor(floorDTO);
	}
	
	@GetMapping("/getListOfFloors")
	private List<FloorDTO> getListOfRooms(@RequestParam Long ownerId, @RequestParam Long buildingId)
	{
		return floorService.getListOfFloors(ownerId,buildingId);
	}
	@PutMapping(value = "/updateFloor")
	public boolean updateFloor(@RequestBody FloorDTO floorDTO)
	{
		return floorService.updateFloor(floorDTO);
	}
	
	@GetMapping(value = "/getlistofFloorsForUpdate")
	public List<FloorDTO> getListofFloorsForUpdate(@RequestParam Long ownerId, @RequestParam Long buildingId) {
		
		return floorService.getListofFloorforUpdate(ownerId, buildingId);
	}
	
}
