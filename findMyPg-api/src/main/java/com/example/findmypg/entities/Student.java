package com.example.findmypg.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "student")
@Getter @Setter @ToString
public class Student extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "first_name")
	private String studfirstname;
	
	@Column(name = "last_name")
	private String studlastname;
	
	@Column(name = "middle_name")
	private String studmiddlename;
	
	@Column(name = "email_id")
	private String studemailid;
	
	@Column(name = "mobile_number")
	private String studmobilenumber;
	
	@Column(name = "username")
	private String studusername;
	
	@ManyToOne
	@JoinColumn(name = "owner_id",nullable = false)
	private Owner owner;
}
