package com.example.findmypg.employee;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class EmployeeDTO {

	private String empfirstName;
	private String empLastName;
	private String empMiddleName;
	private String empMobileNumber;
	private String empUserName;
	private String empEmailId;
	private String ownerid;
}
