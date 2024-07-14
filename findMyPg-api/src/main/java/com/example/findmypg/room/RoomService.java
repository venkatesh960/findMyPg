package com.example.findmypg.room;

import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findmypg.entities.Floor;
import com.example.findmypg.entities.Room;
import com.example.findmypg.floor.FloorRepositry;

@Service
public class RoomService {

	@Autowired
	private FloorRepositry floorRepositry;

	@Autowired
	private RoomRepositry roomrepo;

	public Room addRoom(RoomDTO dto) {
		System.err.println(dto.getBuildingId());
		List<Floor> listofFloors = floorRepositry.findByBuilding_Id(dto.getBuildingId());
		if (!listofFloors.isEmpty()) {
			for (Floor floor : listofFloors) { // 0,1
	
				for (FloorRoomDTO floorRoom : dto.getFloorRooms()) {
					
					for (RoomDetailDTO roomDetail : floorRoom.getRooms()) {
					
						if (floor.getFloorNumber() == floorRoom.getFloorNumber()) {
//							
//							System.out.println("Floor Number: " + floorRoom.getFloorNumber());
//							System.out.println("Room Number: " + roomDetail.getRoomNumber());
//							System.out.println("Shares: " + roomDetail.getShares());
//							System.out.println("Rates: " + roomDetail.getRates());
						
							Room room = new Room();
							room.setFloorId(floor);
							room.setRoomNumber(roomDetail.getRoomNumber());
							room.setShareType(roomDetail.getShares());
							room.setRates(roomDetail.getRates());
							LocalDateTime dateTime = LocalDateTime.now();
//							dateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME);
							room.setCreatedTimeStamp(dateTime);
							roomrepo.save(room);
						}
						
					}
				}
			}
//			return ;
		}
		return null;
	}

	public List<RoomDTO> getListOfRooms(Long floorId) {
		List<Room> listOfRooms = roomrepo.findByFloorId_Id(floorId);
		if (listOfRooms != null) {
			List<RoomDTO> listOfRoomDTO = new ArrayList<RoomDTO>();
			for (Room rooms : listOfRooms) {
				RoomDTO roomDTO = new RoomDTO();
				roomDTO.setId(rooms.getId());
				roomDTO.setRates(rooms.getRates());
				roomDTO.setShares(rooms.getShareType());
				roomDTO.setRoomNumber(rooms.getRoomNumber());
				listOfRoomDTO.add(roomDTO);
			}
			return listOfRoomDTO;

		}
		return null;
	}

}
