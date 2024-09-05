package com.example.findmypg.owner;

import java.util.List;

import com.example.findmypg.entities.Building;
import com.example.findmypg.entities.Floor;
import com.example.findmypg.entities.Owner;
import com.example.findmypg.entities.Room;

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
	
	private List<Floor> listofFloors;
	private List<Room> listofRooms;
	private List<Building> listofBuilding;
	private Owner owner;
						

}
