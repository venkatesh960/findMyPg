package com.example.findmypg.building;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.findmypg.entities.Building;
import com.example.findmypg.owner.MyBuildingDTO;

@RestController
@RequestMapping("/api/findmypg/building")
public class BuildingController {

	@Autowired
	private BuildingService buildingService;

	@PostMapping(value = "/addBuilding")
	private Building addBuilding(@RequestBody BuildingDTO buildingDTO) {
		return buildingService.addBuilding(buildingDTO);
	}

	@GetMapping("/getBuildingDetails")
	public List<BuildingDTO> getBuilding(@RequestParam Long ownerId) {
		return buildingService.getListOfBuilding(ownerId);
	}

	@PutMapping(value = "/updateBuilding")
	public boolean updateBuilding(@RequestBody BuildingDTO buildingDTO) {
		return buildingService.updateBuilding(buildingDTO);
	}

	@GetMapping(value = "/getAvailbleBuilding")
	private List<MyBuildingDTO> getAllAvailableBuilding(@RequestParam Long ownerId) {

		return buildingService.getAllmyAvailbleBuilding(ownerId);
	}
}
