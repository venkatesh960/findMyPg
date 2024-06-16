package com.example.findmypg.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name="owner_registration")
@Getter @Setter  
public class OwnerRegistration extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "middle_name")
	private String middleName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "mobile_number")
	private String mobileNum;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "password")
	private String password;
	

}
