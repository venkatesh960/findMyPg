package com.example.findmypg.owner;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.findmypg.entities.Owner;

@Repository
public interface OwnerRegistrationRepo extends JpaRepository<Owner, Long> {

	Owner findByMobileNum(String mobileNumber);

	Owner findByMobileNumAndUserName(String mobileNumber, String userName);

	Owner findByMobileNumAndPassword(String mobileNumber, String password);
	
	
	
}
