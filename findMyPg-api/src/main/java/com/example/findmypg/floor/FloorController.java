package com.example.findmypg.floor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController("/api/findmypg/floor")
public class FloorController {
	
	@Autowired 
	private FloorService floorService;
	
	@PostMapping("/addFloor")
	private boolean addFloor(@RequestBody FloorDTO floorDTO)
	{
		return floorService.addFloor(floorDTO);
	}

}
