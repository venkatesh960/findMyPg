package com.example.findmypg.owner;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findmypg.building.BuildingDTO;
import com.example.findmypg.building.BuildingService;
import com.example.findmypg.entities.Owner;
import com.example.findmypg.floor.FloorDTO;
import com.example.findmypg.floor.FloorService;
import com.example.findmypg.room.RoomService;

@Service
public class OwnerRegService {

	@Autowired
	private OwnerRegistrationRepo ownerRegiRepo;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private FloorService floorService;
	
	@Autowired 
	private BuildingService buildingService;

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
		Owner ownerLogin = ownerRegiRepo.findByMobileNumAndPassword(mobileNumber, password);
		if (ownerLogin!=null) {
			OwnerRegDTO dto=new OwnerRegDTO();
			dto.setId(ownerLogin.getId());
			dto.setFirstName(ownerLogin.getFirstName());
			dto.setEmailId(ownerLogin.getEmail_Id());
			dto.setMobileNumber(ownerLogin.getMobileNum());
			dto.setUserName(ownerLogin.getUserName());
			dto.setLastName(ownerLogin.getLastName());
			return dto;
		}
		return null;
	}

	public BuildingDTO getMyBuilding(Long ownerId) {
		List<BuildingDTO> listOfBuilding = buildingService.getListOfBuilding(ownerId);
		for (BuildingDTO buildingDTO : listOfBuilding) {
			List<FloorDTO> listOfFloors = floorService.getListOfFloors(buildingDTO.getId());
			
			System.out.println(buildingDTO.getId()+" 2222");
		}
//		floorService.getListOfFloors(ownerId);
		return null;
	}

}
