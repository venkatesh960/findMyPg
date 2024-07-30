package com.example.findmypg.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "student")
@Getter @Setter 
public class Student extends BaseEntity {

	
	@Column(name = "first_name")
	private String studFirstName;
	
	@Column(name = "last_name")
	private String studLastName;
	
	@Column(name = "middle_name")
	private String studmiddlename;
	
	@Column(name = "email_id")
	private String studEmailId;
	
	@Column(name = "mobile_number")
	private String studMobileNumber;
	
//	@Column(name = "username")
//	private String studUsername;
	
	@Column(name = "id_type")
	private String idType;
	
	@Column(name = "id_number")
	private long idNumber;
	
	@Column(name = "building_id")
	private long buildigId;
	
	@Column(name = "floor_id")
	private long floorId;
	
	@Column(name = "room_id")
	private long roomId;
	
	@ManyToOne
	@JoinColumn(name = "owner_id",nullable = false)
	@JsonBackReference
	private Owner owner;
}
