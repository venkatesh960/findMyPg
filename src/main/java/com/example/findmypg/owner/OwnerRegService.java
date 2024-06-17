package com.example.findmypg.owner;

import java.util.List;

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

	public boolean loginChcek(OwnerRegDTO dto) {
		List<Owner> list=ownerRegiRepo.findAll();
		if (list!=null) {
			for (Owner owner : list) {
				if (owner.getMobileNum().equalsIgnoreCase(dto.getMobileNumber()) && owner.getUserName().equalsIgnoreCase(dto.getUserName())) {
					return true;
				}
			}
		}
		return false;
	}

}
