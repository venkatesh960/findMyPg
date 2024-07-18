package com.example.findmypg.owner;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class OwnerRegDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String emailId;
	private String mobileNumber;
	private String userName;
	private String password;
	private List<Long> listofBuildings;
	
}
