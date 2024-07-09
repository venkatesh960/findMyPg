package com.example.findmypg.room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class RoomDTO {
	
	private Long id;
	private  String roomNumber;
	private int shares;
	private int rates;

}
