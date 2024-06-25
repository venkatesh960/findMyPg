package com.example.findmypg.building;

import java.time.LocalDateTime;
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
	
	public boolean addBuilding(BuildingDTO buildingDTO) {
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
			Building save = buildingRepositry.save(building);
			if (save!=null) {
				return true;
			}
			return false;
			
		}
		return false;
	}

}
