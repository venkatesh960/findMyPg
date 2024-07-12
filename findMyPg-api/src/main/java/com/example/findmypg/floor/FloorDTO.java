package com.example.findmypg.floor;

import java.util.List;

import com.example.findmypg.room.RoomDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class FloorDTO {
	
	private Long id;
	private Long buildingId;
	private int floor;
	private int numberofRooms;
	private String selectedBuilding;
	private List<FloorsDetailsDTO> floorsData;
	private List<RoomDTO> listofRooms;
	private String BuildingName;

}
