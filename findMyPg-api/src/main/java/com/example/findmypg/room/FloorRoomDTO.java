package com.example.findmypg.room;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class FloorRoomDTO {
	
	 private int floorNumber;
	    private List<RoomDetailDTO> rooms;

}
