package com.example.findmypg.owner;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyBuildingDTO {
	
	private String pgName;
	private String pgType;
	private int numofFloors;
	private String location;
	private int floor;
	private int numberofRooms;
	private String roomNumber;
	private int shareType;
	private int rates;

}
