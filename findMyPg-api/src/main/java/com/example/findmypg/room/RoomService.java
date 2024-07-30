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
	    Room savedRoom = null;
	    List<Room> listofRooms = roomrepo.findByBuildingId(dto.getBuildingId());
	    if (listofRooms!=null) {
			return null;
		}
	    // Fetch building by name
	    Building building = buildingRepositry.findByPgName(dto.getSelectedBuilding());
	    if (building == null) {
	        return null; // Building not found
	    }
	    
	    // Iterate over floorRoomDTOs
	    for (FloorRoomDTO floorRoomDTO : dto.getFloorRooms()) {
	        
	        // Fetch floor by building ID and floor number
	        Floor floor = floorRepositry.findByBuilding_IdAndFloorNumber(dto.getBuildingId(), floorRoomDTO.getFloorNumber());
	        if (floor == null) {
	            return null; // Floor not found
	        }
	        
	        // Check if rooms already exist on the floor
	        List<Room> listOfRooms = roomrepo.findByFloorId_Id(floor.getId());
	        
	        for (RoomDetailDTO roomDetailDTO : floorRoomDTO.getRooms()) {
	            boolean roomExists = false;
	            
	            // Check if room already exists
	            for (Room existingRoom : listOfRooms) {
	                if (existingRoom.getRoomNumber()==roomDetailDTO.getRoomNumber()) {
	                    roomExists = true;
	                    break;
	                }
	            }
	            
	            if (!roomExists) {
	                // Add new room if it does not exist
	                Room room = new Room();
	                room.setFloorId(floor);
	                room.setRoomNumber(roomDetailDTO.getRoomNumber());
	                room.setShareType(roomDetailDTO.getShares());
	                room.setRates(roomDetailDTO.getRates());
	                room.setBuildingId(dto.getBuildingId());
	                room.setCreatedTimeStamp(LocalDateTime.now());
	                room.setAvailableRooms(roomDetailDTO.getShares());
		            room.setStatus("Available");
	                
	                savedRoom = roomrepo.save(room);
	            }
	        }
	    }
	    
	    return savedRoom; // Return the last saved room
	}



	public List<RoomDTO> getListOfRooms(Long ownerId, long buildingId) {
	    List<RoomDTO> listOfRoomDTO1 = new ArrayList<>();
	    Optional<Owner> owner1 = ownerRegistrationRepo.findById(ownerId);
	    List<Building> buildings = buildingRepositry.findByOwner_Id(ownerId);

	    if (owner1.isPresent() && !buildings.isEmpty()) {
	        for (Building building : buildings) {
	            if (building.getId() == buildingId) {
	                List<Floor> floors = floorRepositry.findByBuilding_Id(buildingId);
	                if (!floors.isEmpty()) {
	                    for (Floor floor : floors) {
	                        List<Room> rooms = roomrepo.findByFloorId_Id(floor.getId());
	                        if (rooms != null && !rooms.isEmpty()) {
	                            for (Room room : rooms) {
	                                RoomDTO roomDTO = new RoomDTO();
	                                roomDTO.setBuildingId(buildingId);
	                                roomDTO.setSelectedBuilding(building.getPgName());
	                                roomDTO.setId(room.getId());
	                                roomDTO.setRates(room.getRates());
	                                roomDTO.setShares(room.getShareType());
	                                roomDTO.setRoomNumber(room.getRoomNumber());
	                                listOfRoomDTO1.add(roomDTO);
	                            }
	                        }
	                    }
	                }
	            }
	        }
	    }
	    return listOfRoomDTO1;
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
							RoomDTO dto = new RoomDTO();
							Room room = listofRooms.get(k);
							dto.setFloorNumber(floor.getFloorNumber());
							dto.setBuildingId(buildingId);
							dto.setRoomNumber(room.getRoomNumber());
							dto.setShares(room.getShareType());
							dto.setRates(room.getRates());
							roomDTOs.add(dto);
						} else {
							RoomDTO dto = new RoomDTO();
							dto.setFloorNumber(floor.getFloorNumber());
							dto.setBuildingId(buildingId);
							roomDTOs.add(dto);
						}
					}
				}
			}
		}
		return roomDTOs;
	}

	public boolean updateRoomDetails(RoomDTO dto) {
		Building building = buildingRepositry.findByPgName(dto.getSelectedBuilding());
        if (building != null) {
            for (FloorRoomDTO floorRoomDTO : dto.getFloorRooms()) {
                Floor floor = floorRepositry.findByBuilding_IdAndFloorNumber(dto.getBuildingId(), floorRoomDTO.getFloorNumber());
                if (floor != null) {
                    for (RoomDetailDTO roomDetailDTO : floorRoomDTO.getRooms()) {
                        Room existingRoom = roomrepo.findByFloorId_IdAndRoomNumber(floor.getId(), roomDetailDTO.getRoomNumber());
                        if (existingRoom != null) {
                            existingRoom.setShareType(roomDetailDTO.getShares());
                            existingRoom.setRates(roomDetailDTO.getRates());
                            existingRoom.setBuildingId(dto.getBuildingId());
                            LocalDateTime dateTime = LocalDateTime.now();
                            existingRoom.setUpdatetimestamp(dateTime);
                            
                            roomrepo.save(existingRoom);
                        } else {
                            Room newRoom = new Room();
                            newRoom.setFloorId(floor);
                            newRoom.setRoomNumber(roomDetailDTO.getRoomNumber());
                            newRoom.setShareType(roomDetailDTO.getShares());
                            newRoom.setRates(roomDetailDTO.getRates());
                            newRoom.setBuildingId(dto.getBuildingId());
                            LocalDateTime dateTime = LocalDateTime.now();
                            newRoom.setCreatedTimeStamp(dateTime);
                            roomrepo.save(newRoom);
                        }
                    }
                } else {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}