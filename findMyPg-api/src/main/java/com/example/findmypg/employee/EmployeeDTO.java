package com.example.findmypg.employee;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class EmployeeDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String mobileNumber;
	private String userName;
	private String emailId;
	private String joingDate;
	private Double salary;
	
}
