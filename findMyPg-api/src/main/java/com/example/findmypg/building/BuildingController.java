package com.example.findmypg.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping
@RestController("/api/findmypg/building")
public class BuildingController {
	
	@Autowired 
	private BuildingService buildingService;
	
	@PostMapping("/addBuilding")
	private boolean addBuilding(@RequestBody BuildingDTO buildingDTO)
	{
		return buildingService.addBuilding(buildingDTO);
	}

}
