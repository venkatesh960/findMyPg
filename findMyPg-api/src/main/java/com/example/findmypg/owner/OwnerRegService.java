package com.example.findmypg.owner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findmypg.building.BuildingRepositry;
import com.example.findmypg.entities.Building;
import com.example.findmypg.entities.Floor;
import com.example.findmypg.entities.Owner;
import com.example.findmypg.entities.Room;


@Service
public class OwnerRegService {

	@Autowired
	private OwnerRegistrationRepo ownerRegiRepo;
	
	@Autowired 
	private BuildingRepositry buildingRepositry;

	public Boolean ownerRegistration(OwnerRegDTO ownerDTO) {

		Owner ownerRegistration = new Owner();
		ownerRegistration.setFirstName(ownerDTO.getFirstName());
		ownerRegistration.setLastName(ownerDTO.getLastName());
		ownerRegistration.setMiddleName(ownerDTO.getMiddleName());
		ownerRegistration.setMobileNum(ownerDTO.getMobileNumber());
		ownerRegistration.setPassword(ownerDTO.getPassword());
		ownerRegistration.setEmail_Id(ownerDTO.getEmailId());
		ownerRegistration.setUserName(ownerDTO.getUserName());
		LocalDateTime localDateandTime = LocalDateTime.now();

		ownerRegistration.setCreatedTimeStamp(localDateandTime);

		Owner check = ownerRegiRepo.save(ownerRegistration);
		if (check != null) {
			return true;
		} else {
			return false;
		}
	}

	public OwnerRegDTO getOwnerDetails(String mobileNumber) {

		Owner owner = ownerRegiRepo.findByMobileNum(mobileNumber);

		if (owner != null) {
			OwnerRegDTO dto = new OwnerRegDTO();
			dto.setFirstName(owner.getFirstName());
			dto.setLastName(owner.getLastName());
			dto.setEmailId(owner.getEmail_Id());
			dto.setMiddleName(owner.getMiddleName());
			dto.setUserName(owner.getUserName());
			dto.setPassword(owner.getPassword());
			return dto;
		} else {
			return null;
		}
	}

	public OwnerRegDTO loginCheck(String mobileNumber,String password) {
		List<Long> buildings=new ArrayList<Long>();
		Owner ownerLogin = ownerRegiRepo.findByMobileNumAndPassword(mobileNumber, password);
		if (ownerLogin!=null) {
			List<Building> listofBuildings = buildingRepositry.findByOwner_Id(ownerLogin.getId());
			if (!listofBuildings.isEmpty()) {
				for (Building building : listofBuildings) {
					buildings.add(building.getId());
				}
			}
			OwnerRegDTO dto=new OwnerRegDTO();
			dto.setId(ownerLogin.getId());
			dto.setFirstName(ownerLogin.getFirstName());
			dto.setEmailId(ownerLogin.getEmail_Id());
			dto.setMobileNumber(ownerLogin.getMobileNum());
			dto.setUserName(ownerLogin.getUserName());
			dto.setLastName(ownerLogin.getLastName());
			dto.setListofBuildings(buildings);
			return dto;
		}
		return null;
	}

	public List<MyBuildingDTO> getMyBuilding(Long ownerId) {
		List<Building> listofBuilding = buildingRepositry.findByOwner_Id(ownerId);
		List<MyBuildingDTO> list=new ArrayList<MyBuildingDTO>();
		for (Building building : listofBuilding) {
			
			for (Floor floors : building.getListofFloors()) {
				
				for (Room rooms : floors.getListofRooms()) {
					
					MyBuildingDTO buildingDTO=new MyBuildingDTO();
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
					
					list.add(buildingDTO);

				}
			}
		}
		return list;
	}

}
