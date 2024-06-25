package com.example.findmypg.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "employee")
 @Getter @Setter
public class Employee extends BaseEntity{



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
	
	@ManyToOne
	@JoinColumn(name = "owner_id",nullable = false)
	@JsonBackReference
	private Owner owner;
	
	
}
