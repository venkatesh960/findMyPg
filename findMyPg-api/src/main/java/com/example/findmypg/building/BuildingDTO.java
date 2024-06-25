package com.example.findmypg.building;

import com.example.findmypg.entities.Building;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BuildingDTO {
	
	private Long id;
	private String pgName;
	private String location;
	private String pgType;
	private int numberofFloors;
	private Building building;

}
