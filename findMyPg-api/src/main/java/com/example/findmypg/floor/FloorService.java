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
		System.out.println(floorDTO);
		int count = floorDTO.getFloorsData().size();
		for (FloorsDetailsDTO floorDetailDTO : floorDTO.getFloorsData()) {
			Optional<Building> buildingDetails = buildingRepositry.findById(floorDetailDTO.getBuildingId());
			List<Floor> listofFloors = floorRepositry.findByBuilding_Id(floorDetailDTO.getBuildingId());
			if (buildingDetails.isPresent() && listofFloors.isEmpty()) {

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
			} else {
				return null;
			}
		}
		return null;
	}

	public List<FloorDTO> getListOfFloors(Long ownerId, Long buildingId) {

		List<Building> listOfBuildings = buildingRepositry.findByOwner_Id(ownerId);
		List<FloorDTO> floorDTOs = new LinkedList<FloorDTO>();
		for (Building building : listOfBuildings) {
			if (building.getId() == buildingId) {

				List<Floor> listofFloors = floorRepositry.findByBuilding_Id(buildingId);
				System.err.println(listofFloors.size() + " List of Floor are ");
				if (!listofFloors.isEmpty()) {
					for (Floor floor : listofFloors) {
						FloorDTO floorDTO = new FloorDTO();
						floorDTO.setId(floor.getId());
						floorDTO.setFloorNumber(floor.getFloorNumber());
						floorDTO.setNumberofRooms(floor.getNumberofRooms());
						floorDTO.setBuildingId(building.getId());
						floorDTO.setBuildingName(building.getPgName());
						floorDTOs.add(floorDTO);

					}
				}
			}
		}
		return floorDTOs;
	}

	public List<FloorDTO> getListofFloorforUpdate(Long ownerId, Long buildingId) {
		List<FloorDTO> floorDTOs = new LinkedList<FloorDTO>();
		List<Building> listofBuilding = buildingRepositry.findByOwner_Id(ownerId);
		for (Building building : listofBuilding) {
			if (building.getId() == buildingId) {
				List<Floor> listofFloors = floorRepositry.findByBuilding_Id(buildingId);
				for (int i = 0; i < building.getNumberofFloors(); i++) {
					if (listofFloors.size() > i) {
						Floor floor = listofFloors.get(i);
						FloorDTO floorDTO = new FloorDTO();
						floorDTO.setId(floor.getId());
						floorDTO.setFloorNumber(floor.getFloorNumber());
						floorDTO.setNumberofRooms(floor.getNumberofRooms());
						floorDTO.setBuildingId(building.getId());
						floorDTO.setBuildingName(building.getPgName());
						floorDTOs.add(floorDTO);
					} else {
						FloorDTO dto = new FloorDTO();
						dto.setBuildingName(building.getPgName());
						dto.setFloorNumber(i);
						floorDTOs.add(dto);
					}

				}

			}
		}
		return floorDTOs;
	}

	public boolean updateFloor(FloorDTO floorDTO) {
		
		Optional<Building> building = buildingRepositry.findById(floorDTO.getBuildingId());		
		List<Floor> listofFloors = floorRepositry.findByBuilding_Id(floorDTO.getBuildingId());
		int count=0;
		if (building.isPresent() && listofFloors!=null) {
			for (FloorsDetailsDTO floorDetailDTO : floorDTO.getFloorsData()) { //0,1,2,3
				System.out.println(floorDetailDTO.getFloorNumber()+" Iam for floor count");
				if (count<listofFloors.size()) {// 0,1<2
					System.err.println(count +" Iam counting here ");
					Floor floor = listofFloors.get(count++);
					if (floor!=null && floor.getFloorNumber() == floorDetailDTO.getFloorNumber()) { 
						floor.setBuilding(building.get());
						
						floor.setFloorNumber(floorDetailDTO.getFloorNumber());
						floor.setNumberofRooms(floorDetailDTO.getNumberofRooms());
						LocalDateTime localDateandTime = LocalDateTime.now();
						floor.setUpdatetimestamp(localDateandTime);
						floorRepositry.save(floor);
					}
				}else {
					System.out.println("This is my first entry ");
					Floor newFloor=new  Floor();
					newFloor.setBuilding(building.get());
					newFloor.setFloorNumber(floorDetailDTO.getFloorNumber());
					newFloor.setNumberofRooms(floorDetailDTO.getNumberofRooms());
					LocalDateTime localDateandTime = LocalDateTime.now();
					newFloor.setCreatedTimeStamp(localDateandTime);
					floorRepositry.save(newFloor);
				}
			}
			return true;
		}
		return false;
	}

}
