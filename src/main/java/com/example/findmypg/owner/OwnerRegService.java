package com.example.findmypg.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findmypg.entities.OwnerRegistration;


@Service
public class OwnerRegService {
	
	@Autowired 
	private OwnerRegistrationRepo ownerRegiRepo;
	
	public Boolean ownerRegistration(OwnerRegDTO ownerDTO) {
	
		OwnerRegistration ownerRegistration=new OwnerRegistration();
		ownerRegistration.setFirstName(ownerDTO.getFirstName());
		ownerRegistration.setLastName(ownerDTO.getLastName());
		ownerRegistration.setMiddleName(ownerDTO.getMiddleName());
		ownerRegistration.setMobileNum(ownerDTO.getMobileNumber());
		ownerRegistration.setPassword(ownerDTO.getPassword());
		ownerRegistration.setUserName(ownerDTO.getUserName());
		
		OwnerRegistration check=ownerRegiRepo.save(ownerRegistration);
		if (check!=null) {
			return true;
		} else {
			return false;
		}
	}

	public OwnerRegDTO getOwnerDetails(String mobileNumber) {
//		System.out.println(ownerRegiRepo.findById(1l));
		OwnerRegistration ownerRegistration=ownerRegiRepo.findByMobileNum(mobileNumber);
		System.out.println(ownerRegistration.toString());
		return null;
	}

}
