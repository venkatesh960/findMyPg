package com.example.findmypg.building;

import java.util.List;
import com.example.findmypg.floor.FloorDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BuildingDTO {
	
	private Long id;
	private String pgName;
	private String location;
	private String pgType;
	private int numberofFloors;
	private List<FloorDTO> listofFloors;
	private String selectedBuilding;
	
	private long ownerId;
	
	private int num_of_floors;
	private int floor;
	private int number_of_rooms;
	private int room_number;
	private String share_type;
	private double rates;
	private int floor_id;
	
	private String buildingImage;
	


}
