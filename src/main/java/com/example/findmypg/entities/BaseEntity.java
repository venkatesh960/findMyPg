package com.example.findmypg.entities;

import java.io.Serializable;

//import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter 
public class BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column(name="created")
//	private String createdTimeStamp;
//	
//	@Column(name="createdBy")
//	private Long createdById;
//	
//	@Column(name = "updatedBy")
//	private String updatedTime;
}
