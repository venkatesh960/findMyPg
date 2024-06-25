package com.example.findmypg.floor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
