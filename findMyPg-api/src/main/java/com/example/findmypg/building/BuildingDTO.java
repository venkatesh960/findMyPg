package com.example.findmypg.building;

import java.util.List;

import com.example.findmypg.floor.FloorDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BuildingDTO {
	
	private Long id;
	private String pgName;
	private String location;
	private String pgType;
	private int numberofFloors;
	private List<FloorDTO> listofFloors;
}
