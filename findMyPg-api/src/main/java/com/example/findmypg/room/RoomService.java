package com.example.findmypg.room;

import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findmypg.building.BuildingRepositry;
import com.example.findmypg.entities.Building;
import com.example.findmypg.entities.Floor;
import com.example.findmypg.entities.Owner;
import com.example.findmypg.entities.Room;
import com.example.findmypg.floor.FloorRepositry;
import com.example.findmypg.owner.OwnerRegistrationRepo;

@Service
public class RoomService {

	@Autowired
	private FloorRepositry floorRepositry;

	@Autowired
	private RoomRepositry roomrepo;

	@Autowired
	private OwnerRegistrationRepo ownerRegistrationRepo;

	@Autowired
	private BuildingRepositry buildingRepositry;

	public Room addRoom(RoomDTO dto) {
		
		System.err.println(dto.getBuildingId());
		Room save = null;
		List<Floor> listofFloors = floorRepositry.findByBuilding_Id(dto.getBuildingId());
		if (!listofFloors.isEmpty()) {
			for (Floor floor : listofFloors) {
				List<Room> listofRooms = roomrepo.findByFloorId_Id(floor.getId());
				System.out.println(listofRooms);
				if (listofRooms==null) {
					for (FloorRoomDTO floorRoom : dto.getFloorRooms()) {

						for (RoomDetailDTO roomDetail : floorRoom.getRooms()) {

							if (floor.getFloorNumber() == floorRoom.getFloorNumber()) {
								Room room = new Room();
								room.setFloorId(floor);
								room.setRoomNumber(roomDetail.getRoomNumber());
								room.setShareType(roomDetail.getShares());
								room.setRates(roomDetail.getRates());
								LocalDateTime dateTime = LocalDateTime.now();
//							dateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME);
								room.setCreatedTimeStamp(dateTime);
								save = roomrepo.save(room);
							}

						}
					}
				} else {
					return null;
				}
			}
			return save;
		}
		return null;
	}

	public List<RoomDTO> getListOfRooms(Long ownerId, long buildingId) {
		List<RoomDTO> listOfRoomDTO1 = new ArrayList<RoomDTO>();
		Optional<Owner> owner1 = ownerRegistrationRepo.findById(ownerId);
		List<Building> byOwner_Id = buildingRepositry.findByOwner_Id(ownerId);
		if (owner1.isPresent() && !byOwner_Id.isEmpty()) {
			for (Building building : byOwner_Id) {
				if (building.getId() == buildingId) {
					List<Floor> byBuilding_Id = floorRepositry.findByBuilding_Id(building.getId());
					if (!byBuilding_Id.isEmpty()) {
						for (Floor floor : byBuilding_Id) {
							List<Room> listOfRooms = roomrepo.findByFloorId_Id(floor.getId());
							if (listOfRooms != null) {
								for (Room rooms : listOfRooms) {
									RoomDTO roomDTO = new RoomDTO();
									roomDTO.setBuildingId(buildingId);
									roomDTO.setSelectedBuilding(building.getPgName());
									roomDTO.setId(rooms.getId());
									roomDTO.setRates(rooms.getRates());
									roomDTO.setShares(rooms.getShareType());
									roomDTO.setRoomNumber(rooms.getRoomNumber());
									listOfRoomDTO1.add(roomDTO);
								}
							}

						}
					}
				}
			}
			return listOfRoomDTO1;
		} else {
			return listOfRoomDTO1;
		}
	}

}
