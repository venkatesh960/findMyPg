package com.example.findmypg.floor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
	public Floor addFloor(FloorDTO floorDTO) {
		 Optional<Building> buildingDetails = buildingRepositry.findById(floorDTO.getId());
		if (buildingDetails.isPresent()) {
			Floor floor =new Floor();
			
			floor.setBuilding(buildingDetails.get());
			floor.setFloor(floorDTO.getFloor());
			floor.setNumberofRooms(floorDTO.getNumberofRooms());
			LocalDateTime localDateandTime = LocalDateTime.now();
			floor.setCreatedTimeStamp(localDateandTime);
			
			return floorRepositry.save(floor);
			
		}
		return null;
	}
	public List<FloorDTO> getListOfRooms(Long buildingId) {
		
			 List<Floor> listofFloors = floorRepositry.findByBuilding_Id(buildingId);
			 List<FloorDTO> floorDTOs=new ArrayList<FloorDTO>();
			 if (!listofFloors.isEmpty()) {
				for (Floor floor : listofFloors) {
					FloorDTO floorDTO=new FloorDTO();
					floorDTO.setFloor(floor.getFloor());
					floorDTO.setNumberofRooms(floor.getNumberofRooms());
					floorDTO.setBuildingId(buildingId);
					floorDTOs.add(floorDTO);
					
				}
				return floorDTOs;
			}
			
			return null;
	}

}
