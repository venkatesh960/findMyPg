package com.example.findmypg.owner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.findmypg.entities.OwnerRegistration;


@Repository
public interface OwnerRegistrationRepo extends JpaRepository<OwnerRegistration, Long> {

	com.example.findmypg.entities.OwnerRegistration findByMobileNum(String mobileNumber);

}
