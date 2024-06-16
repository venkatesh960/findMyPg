package com.example.findmypg.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "student")
@Getter @Setter
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
	
	@Column(name = "owner_id")
	private String ownerid;
}
