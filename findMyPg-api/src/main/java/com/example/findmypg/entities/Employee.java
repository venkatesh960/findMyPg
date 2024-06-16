package com.example.findmypg.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "employee")
 @Getter @Setter
public class Employee extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@Column(name = "emp_firstname")
	private String empFirstName;
	
	@Column(name = "emp_lastname")
	private String empLastName;
	
	@Column(name = "emp_middlename")
	private String empMiddleName;
	
	@Column(name = "emp_email_id")
	private String empEmailId;
	
	@Column(name = "emp_mobile_num")
	private String empMobileNumber;
	
	@Column(name = "emp_username")
	private String empUsername;
	
	@Column(name = "owner_id")
	private String ownerid;
}
