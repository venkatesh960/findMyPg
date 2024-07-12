package com.example.findmypg.floor;

import java.time.LocalDateTime;
import java.util.LinkedList;
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

	@Autowired
	private BuildingRepositry buildingRepositry;


	public Floor addFloor(FloorDTO floorDTO) {
		int count = floorDTO.getFloorsData().size();
		for (FloorsDetailsDTO floorDetailDTO : floorDTO.getFloorsData()) {
			Optional<Building> buildingDetails = buildingRepositry.findById(floorDetailDTO.getBuildingId());
			if (buildingDetails.isPresent()) {

				Floor floor = new Floor();
				floor.setBuilding(buildingDetails.get());
				floor.setFloorNumber(floorDetailDTO.getFloorNumber());
				floor.setNumberofRooms(floorDetailDTO.getNumberofRooms());
				LocalDateTime localDateandTime = LocalDateTime.now();
				floor.setCreatedTimeStamp(localDateandTime);
				Floor save = floorRepositry.save(floor);
				count--;
				if (count <= 0) {
					return save;
				}
			}
		}
		return null;
	}

	public List<FloorDTO> getListOfFloors(Long ownerId) {
	
		List<Building> listOfBuildings = buildingRepositry.findByOwner_Id(ownerId);
		List<FloorDTO> floorDTOs = new LinkedList<FloorDTO>();
		for (Building building : listOfBuildings) {
			List<Floor> listofFloors = floorRepositry.findByBuilding_Id(building.getId());
			if (!listofFloors.isEmpty()) {
				for (Floor floor : listofFloors) {
					FloorDTO floorDTO = new FloorDTO();
					floorDTO.setId(floor.getId());
					floorDTO.setFloor(floor.getFloorNumber());
					floorDTO.setNumberofRooms(floor.getNumberofRooms());
					floorDTO.setBuildingId(building.getId());
					floorDTO.setBuildingName(building.getPgName());
					floorDTOs.add(floorDTO);
					
				}
			}
		}
		return floorDTOs;
	}

}
