package com.example.findmypg.room;

import java.time.LocalDateTime;
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
	public Boolean addRoom(RoomDTO dto) {
		Optional<Floor> floorDetails = floorRepositry.findById(dto.getId());
		if (floorDetails.isPresent()) {
			Room room=new Room();
			room.setFloorId(floorDetails.get());
			room.setRoomNumber(dto.getRoomNumber());
			room.setShareType(dto.getShares());
			room.setRates(dto.getRates());
			LocalDateTime dateTime=LocalDateTime.now();
			room.setCreatedTimeStamp(dateTime);
			Room save = roomrepo.save(room);
			if (save!=null) {
				return true;
			}
			return false;
		}
		return false;
	}

}
