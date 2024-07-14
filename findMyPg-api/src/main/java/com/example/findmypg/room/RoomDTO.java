package com.example.findmypg.room;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @ToString
public class RoomDTO {
	
	private long id;
	private long buildingId;
    private String selectedBuilding;
    private int roomNumber;
    private int shares;
    private int rates;
  
 
    private List<FloorRoomDTO> floorRooms;

    // Getters and Setters
}

