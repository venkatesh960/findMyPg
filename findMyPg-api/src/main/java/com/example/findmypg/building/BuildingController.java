package com.example.findmypg.building;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.findmypg.entities.Building;
import com.example.findmypg.owner.MyBuildingDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/findmypg/building")
public class BuildingController {

	@Autowired
	private BuildingService buildingService;
	
	@PostMapping(value = "/addBuilding", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Building> addBuilding(@RequestPart("building") String buildingDTOString,
	                                            @RequestPart("file") MultipartFile file) throws IOException {
	    ObjectMapper objectMapper = new ObjectMapper();
	    BuildingDTO buildingDTO = objectMapper.readValue(buildingDTOString, BuildingDTO.class);
	    Building building = buildingService.addBuilding(buildingDTO, file);
	    return new ResponseEntity<>(building, HttpStatus.CREATED);
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
