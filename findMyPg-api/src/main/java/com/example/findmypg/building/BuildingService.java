package com.example.findmypg.building;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findmypg.entities.Building;
import com.example.findmypg.entities.Owner;
import com.example.findmypg.owner.OwnerRegistrationRepo;

@Service
public class BuildingService {
	
	@Autowired
	private BuildingRepositry buildingRepositry;
	
	@Autowired
	private OwnerRegistrationRepo ownerRegistrationRepo;
	
	public Building addBuilding(BuildingDTO buildingDTO) {
		Optional<Owner> ownerDetails = ownerRegistrationRepo.findById(buildingDTO.getId());
		if (ownerDetails.isPresent()) {
			Building building=new Building();
			
			building.setPgName(buildingDTO.getPgName());
			building.setLocation(buildingDTO.getLocation());
			building.setPgType(buildingDTO.getPgType());
			building.setNumberofFloors(buildingDTO.getNumberofFloors());
			building.setOwner(ownerDetails.get());
			
			LocalDateTime localDateandTime = LocalDateTime.now();
			building.setCreatedTimeStamp(localDateandTime);
			return buildingRepositry.save(building);
			
			
		}
		return null;
	}

	public List<BuildingDTO> getListOfBuilding(Long ownerId) {
		
		 List<Building> listofBuilding = buildingRepositry.findByOwner_Id(ownerId);
		 List<BuildingDTO> buildingDTOs=new ArrayList<BuildingDTO>();
		 if (!listofBuilding.isEmpty()) {
			for (Building building : listofBuilding) {
				BuildingDTO buildingDTO2=new BuildingDTO();
				buildingDTO2.setLocation(building.getLocation());
				buildingDTO2.setNumberofFloors(building.getNumberofFloors());
				buildingDTO2.setPgName(building.getPgName());
				buildingDTO2.setPgType(building.getPgType());
				buildingDTO2.setId(building.getId());
				buildingDTOs.add(buildingDTO2);
			}
			return buildingDTOs;
		}
		
		return null;
	}

}
