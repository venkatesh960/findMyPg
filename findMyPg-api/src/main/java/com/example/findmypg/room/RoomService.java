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
		Room save = null;
		Building building = buildingRepositry.findByPgName(dto.getSelectedBuilding());
//		System.out.println("Building >> "+building);
		if (building != null) {
			for (FloorRoomDTO floorRoomDTO : dto.getFloorRooms()) {
//				System.out.println(dto.getBuildingId() +" ** "+floorRoomDTO.getFloorNumber());
				Floor floor = floorRepositry.findByBuilding_IdAndFloorNumber(dto.getBuildingId(),
						floorRoomDTO.getFloorNumber());
//				System.out.println("Floor >> "+floor);
				if (floor != null) {
//					System.out.println("Floor Id ==> "+floor.getId());
					List<Room> listofRooms = roomrepo.findByFloorId_Id(floor.getId());
//					System.out.println(listofRooms);
					if (listofRooms == null || listofRooms.isEmpty()) {

						for (RoomDetailDTO roomDetailDTO : floorRoomDTO.getRooms()) {
//							System.out.println(floor.getFloorNumber()+" @@@@ "+floorRoomDTO.getFloorNumber());
							if (floor.getFloorNumber() == floorRoomDTO.getFloorNumber()) {
								System.err.println("Iam creating new Room ");
								Room room = new Room();
								room.setFloorId(floor);
								room.setRoomNumber(roomDetailDTO.getRoomNumber());
								room.setShareType(roomDetailDTO.getShares());
								room.setRates(roomDetailDTO.getRates());
								room.setBuildingId(dto.getBuildingId());
								LocalDateTime dateTime = LocalDateTime.now();
//								dateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME);
								room.setCreatedTimeStamp(dateTime);
								save = roomrepo.save(room);
							}
						}
					} else {
						return null;
					}
				} else {
					return null;
				}
			}
			return save;
		} else {
			return null;
		}
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

	public List<RoomDTO> getListofRoomforUpdate(long ownerId, long buildingId) {
		int count = 0;
		List<RoomDTO> roomDTOs = new ArrayList<RoomDTO>();
		Optional<Owner> owner = ownerRegistrationRepo.findById(ownerId);
		List<Floor> listofFloors = floorRepositry.findByBuilding_Id(buildingId); // {Fid:6,7,8,9}
																					// {fn:0,1,2,3},{NR:2,2,2,3}
		if (listofFloors != null && owner != null) {
			for (int i = 0; i < listofFloors.size(); i++) {
				if (count < listofFloors.size()) {

					Floor floor = listofFloors.get(count++);
					for (int k = 0; k < floor.getNumberofRooms(); k++) {
						List<Room> listofRooms = roomrepo.findByFloorId_Id(floor.getId());// {fid:6,6,7,7,8,8}
						if (listofRooms.size() > k) {
							Room room = listofRooms.get(k);

							RoomDTO dto = new RoomDTO();
							dto.setBuildingId(buildingId);
							dto.setRoomNumber(room.getRoomNumber());
							dto.setShares(room.getShareType());
							dto.setRates(room.getRates());
							roomDTOs.add(dto);
						}else {
							RoomDTO dto = new RoomDTO();
							dto.setBuildingId(buildingId);
							roomDTOs.add(dto);
						}
					}
				}
			}
		}
		return roomDTOs;
	}

}
