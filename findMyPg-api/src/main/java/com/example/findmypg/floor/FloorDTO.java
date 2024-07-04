package com.example.findmypg.floor;

import com.example.findmypg.entities.Building;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FloorDTO {
	
	private Long id;
	private Long buildingId;
	private int floor;
	private int numberofRooms;
	private Building building;

}
