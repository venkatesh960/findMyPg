package com.example.findmypg.room;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		
		Optional<Floor> floorDetails = floorRepositry.findById(dto.getId());
		if (floorDetails.isPresent()) {
			Room room=new Room();
			room.setFloorId(floorDetails.get());
			room.setRoomNumber(dto.getRoomNumber());
			room.setShareType(dto.getShares());
			room.setRates(dto.getRates());
			LocalDateTime dateTime=LocalDateTime.now();
			room.setCreatedTimeStamp(dateTime);
			return roomrepo.save(room);
		}
		return null;
	}
	public List<RoomDTO> getListOfRooms(Long floorId) {
		List<Room> listOfRooms = roomrepo.findByFloorId_Id(floorId);
		if (listOfRooms!=null) {
			List<RoomDTO> listOfRoomDTO=new ArrayList<RoomDTO>();
			for (Room rooms : listOfRooms) {
				RoomDTO roomDTO=new RoomDTO();
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
