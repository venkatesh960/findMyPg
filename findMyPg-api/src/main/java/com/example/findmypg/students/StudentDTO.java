package com.example.findmypg.students;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class StudentDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String mobileNumber;
	private String userName;
	private String emailId;
}
