package com.example.findmypg.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter 
@MappedSuperclass
public class BaseEntity {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "createdtimestamp")
	private String createdTimeStamp;

	
	@Column(name = "createdBy")
	private String createdBy;
	
	@Column(name = "updatetimestamp")
	private String updatetimestamp;
	
	
}
