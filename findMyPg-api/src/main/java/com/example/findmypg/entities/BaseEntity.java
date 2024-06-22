package com.example.findmypg.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter 
@MappedSuperclass
public class BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "createdtimestamp")
	private LocalDateTime createdTimeStamp;
	
	@Column(name = "createdBy")
	private String createdBy;
	
	@Column(name = "updatetimestamp")
	private String updateTimeStamp;
	
	
}
