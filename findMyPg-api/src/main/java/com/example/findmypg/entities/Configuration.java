package com.example.findmypg.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
@Entity(name = "configuration")
public class Configuration extends BaseEntity{

	@Column(name = "config_key")
	private String key;
	
	@Column(name = "config_value")
	private String value;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	@JsonBackReference
	private Owner owner;
}
