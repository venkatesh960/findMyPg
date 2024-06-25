package com.example.findmypg.room;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoomDTO {
	
	private Long id;
	private int floorId;
	private  String roomNumber;
	private int shares;
	private int rates;

}
