package com.example.findmypg.room;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoomDTO {
	
	private long id;
	private long buildingId;
    private String selectedBuilding;
    private int roomNumber;
    private int shares;
    private int rates;
    private long floorNumber;
 
    private List<FloorRoomDTO> floorRooms;
}

