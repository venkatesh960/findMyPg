package com.example.findmypg.floor;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findmypg.building.BuildingRepositry;
import com.example.findmypg.entities.Building;
import com.example.findmypg.entities.Floor;

@Service
public class FloorService {

	@Autowired
	private FloorRepositry floorRepositry;
	
	@Autowired BuildingRepositry buildingRepositry;
	public boolean addFloor(FloorDTO floorDTO) {
		 Optional<Building> buildingDetails = buildingRepositry.findById(floorDTO.getId());
		if (buildingDetails.isPresent()) {
			Floor floor =new Floor();
			
			floor.setBuilding(buildingDetails.get());
			floor.setFloor(floorDTO.getFloor());
			floor.setNumberofRooms(floorDTO.getNumberofRooms());
			LocalDateTime localDateandTime = LocalDateTime.now();
			floor.setCreatedTimeStamp(localDateandTime);
			
			Floor save = floorRepositry.save(floor);
			if (save!=null) {
				return true;
			}
			return false;
			
		}
		return false;
	}

}
