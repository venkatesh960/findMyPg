package com.example.findmypg.building;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.findmypg.entities.Building;
import com.example.findmypg.entities.Floor;
import com.example.findmypg.entities.Owner;
import com.example.findmypg.entities.Room;
import com.example.findmypg.owner.MyBuildingDTO;
import com.example.findmypg.owner.OwnerRegistrationRepo;

@Service
public class BuildingService {

	@Autowired
	private BuildingRepositry buildingRepositry;

	@Autowired
	private OwnerRegistrationRepo ownerRegistrationRepo;

	public Building addBuilding(BuildingDTO buildingDTO, MultipartFile file) throws IOException {
	    System.out.println(buildingDTO.getId() + " Owner Id ");
	    Optional<Owner> ownerDetails = ownerRegistrationRepo.findById(buildingDTO.getId());
	    if (ownerDetails.isPresent()) {
	        Building building = new Building();

	        building.setPgName(buildingDTO.getPgName());
	        building.setLocation(buildingDTO.getLocation());
	        building.setPgType(buildingDTO.getPgType());
	        building.setNumberofFloors(buildingDTO.getNumberofFloors());
	        building.setOwner(ownerDetails.get());

	        if (file != null && !file.isEmpty()) {
	            byte[] photo = file.getBytes();
	            building.setBuildingImage(photo);
	        }

	        LocalDateTime localDateandTime = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedDateAndTime = localDateandTime.format(formatter);

	        building.setCreatedTimeStamp(formattedDateAndTime);
	        return buildingRepositry.save(building);
	    }
	    return null;
	}


	public List<BuildingDTO> getListOfBuilding(Long ownerId) {

		List<Building> listofBuilding = buildingRepositry.findByOwner_Id(ownerId);
		List<BuildingDTO> buildingDTOs = new ArrayList<BuildingDTO>();
		if (!listofBuilding.isEmpty()) {
			for (Building building : listofBuilding) {
				BuildingDTO buildingDTO2 = new BuildingDTO();
				buildingDTO2.setId(building.getId());
				buildingDTO2.setLocation(building.getLocation());
				buildingDTO2.setNumberofFloors(building.getNumberofFloors());
				buildingDTO2.setPgName(building.getPgName());
				buildingDTO2.setPgType(building.getPgType());
				
				// Convert image to Base64 and set in DTO
		        if (building.getBuildingImage() != null) {
		            String base64Image = Base64.getEncoder().encodeToString(building.getBuildingImage());
		            buildingDTO2.setBuildingImage(base64Image);
		        }
				buildingDTOs.add(buildingDTO2);
			}
			return buildingDTOs;
		}

		return buildingDTOs;
	}

	public boolean updateBuilding(BuildingDTO buildingDTO) {
		Optional<Owner> owner = ownerRegistrationRepo.findById(buildingDTO.getOwnerId());
		Building building = buildingRepositry.findByPgName(buildingDTO.getSelectedBuilding());
		if (owner.isPresent() && building != null) {

			building.setPgName(buildingDTO.getPgName());
			building.setLocation(buildingDTO.getLocation());
			building.setPgType(buildingDTO.getPgType());
			building.setNumberofFloors(buildingDTO.getNumberofFloors());
			building.setOwner(owner.get());
			Building save = buildingRepositry.save(building);

			if (save != null) {
				return true;
			}
			return false;
		}
		return false;
	}

	public List<MyBuildingDTO> getAllmyAvailbleBuilding(Long ownerId) {

		List<Building> listofBuilding = buildingRepositry.findByOwner_Id(ownerId);
		List<MyBuildingDTO> list = new ArrayList<MyBuildingDTO>();
		for (Building building : listofBuilding) {

			for (Floor floors : building.getListofFloors()) {

				for (Room rooms : floors.getListofRooms()) {
//						if(rooms.getStatus().equalsIgnoreCase("Available")) {

					MyBuildingDTO buildingDTO = new MyBuildingDTO();
					buildingDTO.setPgName(building.getPgName());
					buildingDTO.setPgType(building.getPgType());
					buildingDTO.setNumofFloors(building.getNumberofFloors());
					buildingDTO.setLocation(building.getLocation());

					buildingDTO.setFloorNumber(floors.getFloorNumber());
					buildingDTO.setNumberofRooms(floors.getNumberofRooms());

					buildingDTO.setRoomNumber(rooms.getRoomNumber());
					buildingDTO.setShareType(rooms.getShareType());
					buildingDTO.setRates(rooms.getRates());
					buildingDTO.setFloorNumber(rooms.getFloorId().getFloorNumber());
					buildingDTO.setAvailableRooms(rooms.getAvailableRooms());
					buildingDTO.setStatus(rooms.getStatus());

					buildingDTO.setBuildingId(building.getId());
					buildingDTO.setFloorId(floors.getId());
					buildingDTO.setRoomId(rooms.getId());

					list.add(buildingDTO);
				}
//					}
			}
		}
		return list;
	}

}
