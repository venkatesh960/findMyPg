package com.example.findmypg.owner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyBuildingDTO {
	
	private String pgName;
	private String pgType;
	private int numofFloors;
	private String location;
	private int floorNumber;
	private int numberofRooms;
	private int roomNumber;
	private int shareType;
	private int rates;
	private String status;
	private int availableRooms;
	
	private long buildingId;
	private long floorId;
	private long roomId;

}
