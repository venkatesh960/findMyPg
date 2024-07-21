package com.example.findmypg.floor;

import java.util.List;

import com.example.findmypg.room.RoomDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class FloorDTO {
	
	private Long id;
	private Long buildingId;
	private int floorNumber;
	private int numberofRooms;
	private String selectedBuilding;
	private List<FloorsDetailsDTO> floorsData;
	private List<RoomDTO> listofRooms;
	private String buildingName;
		
	private long ownerId;
	

}
