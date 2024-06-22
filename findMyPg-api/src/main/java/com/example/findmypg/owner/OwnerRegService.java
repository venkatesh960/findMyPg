package com.example.findmypg.owner;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findmypg.entities.Owner;

@Service
public class OwnerRegService {

	@Autowired
	private OwnerRegistrationRepo ownerRegiRepo;

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

}
