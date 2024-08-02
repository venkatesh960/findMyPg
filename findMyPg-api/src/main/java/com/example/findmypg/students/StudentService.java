package com.example.findmypg.students;

import java.time.LocalDateTime;
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
import com.example.findmypg.entities.Student;
import com.example.findmypg.floor.FloorRepositry;
import com.example.findmypg.owner.OwnerRegistrationRepo;
import com.example.findmypg.room.RoomRepositry;

@Service
public class StudentService {

	@Autowired 
	private StudentRepositry studentrepositry;
	
	@Autowired 
	private OwnerRegistrationRepo ownerRegistrationRepo;
	
	@Autowired 
	private RoomRepositry roomRepositry;
	
	@Autowired
	private BuildingRepositry buildingRepositry;
	
	@Autowired
	private FloorRepositry floorRepositry;

	
	public boolean addStudents(StudentDTO studentDTO) {
		
		Optional<Owner> ownerDetails = ownerRegistrationRepo.findById(studentDTO.getId());
		if (ownerDetails.isPresent()) {
			Student student2 = studentrepositry.findByStudEmailIdAndStudMobileNumber(studentDTO.getEmailId(),studentDTO.getMobileNumber());
			if (student2!=null) {
				return false;
			}
			Student student=new Student();
			student.setStudFirstName(studentDTO.getFirstName());
			student.setStudLastName(studentDTO.getLastName());
			student.setStudmiddlename(studentDTO.getMiddleName());
//			student.setStudusername(studentDTO.getUserName());
			student.setStudEmailId(studentDTO.getEmailId());
			student.setStudMobileNumber(studentDTO.getMobileNumber());
			student.setIdType(studentDTO.getIdType());
			student.setIdNumber(studentDTO.getIdNumber());
			
			LocalDateTime dateAndTime = LocalDateTime.now();
			student.setCreatedTimeStamp(dateAndTime);
			student.setOwner(ownerDetails.get());
			Student check=studentrepositry.save(student);
			System.err.println("Student Entitu "+student);
			if (check!=null) {
				return true;
			}
			return false;
		}
		return false;
	}

	public boolean assignRoomtoStudent(StudentDTO studentDTO) {
		Optional<Owner> ownerDetails = ownerRegistrationRepo.findById(studentDTO.getId());
		if (ownerDetails.isPresent()) {
			Student student2 = studentrepositry.findByStudEmailIdAndStudMobileNumber(studentDTO.getEmailId(),studentDTO.getMobileNumber());
			if (student2!=null) {
				return false;
			}
			Optional<Room> room = roomRepositry.findById(studentDTO.getRoomId());
			if (!room.isPresent()) {
				return false;
			}
			Student student=new Student();
			student.setStudFirstName(studentDTO.getFirstName());
			student.setStudLastName(studentDTO.getLastName());
			student.setStudmiddlename(studentDTO.getMiddleName());

			student.setBuildigId(studentDTO.getBuildingId());
			student.setFloorId(studentDTO.getFloorId());
			student.setRoomId(studentDTO.getRoomId());
//			student.setStudusername(studentDTO.getUserName());
			
			student.setStudEmailId(studentDTO.getEmailId());
			student.setStudMobileNumber(studentDTO.getMobileNumber());
			student.setIdType(studentDTO.getIdType());
			student.setIdNumber(studentDTO.getIdNumber());
			student.setJoiningDate(studentDTO.getJoiningDate());
			
			LocalDateTime dateAndTime = LocalDateTime.now();
			student.setCreatedTimeStamp(dateAndTime);
			student.setOwner(ownerDetails.get());
			
			Room room2=room.get();
			int availableRoom=room2.getAvailableRooms();
			room2.setAvailableRooms(availableRoom-1);
			Room save = roomRepositry.save(room2);
			
			if (room2.getAvailableRooms()==0) {
				room2.setStatus("Unavailable");
				roomRepositry.save(room2);
				
			}
			Student check=studentrepositry.save(student);
			
			System.err.println("Student Entitu "+student);
			if (check!=null && save!=null) {
				return true;
			}
			return false;
		}
		return false;
	}

	public List<StudentDTO> getAllStudentAssignedRooms(String pgName) {
		
		List<StudentDTO> studentDTOs=new ArrayList<StudentDTO>();
		Building building = buildingRepositry.findByPgName(pgName);
		if (building==null) {
			return null;
		}
		List<Student> listofStudent = studentrepositry.findByBuildigId(building.getId());
		if (listofStudent==null) {
			return null;
		}
		for (Student student : listofStudent) {
			StudentDTO studentDTO=new StudentDTO();
			studentDTO.setFirstName(student.getStudFirstName());
			studentDTO.setLastName(student.getStudLastName());
			studentDTO.setEmailId(student.getStudEmailId());
			studentDTO.setMobileNumber(student.getStudMobileNumber());
			studentDTO.setJoiningDate(student.getJoiningDate());
			Optional<Floor> floor = floorRepositry.findById(student.getFloorId());
			if (floor.isPresent()) {
				studentDTO.setFloorNumber(floor.get().getFloorNumber());
			}
			Optional<Room> room = roomRepositry.findById(student.getRoomId());
			if (room.isPresent()) {
				studentDTO.setRoomNumber(room.get().getRoomNumber());
			}
			
			studentDTOs.add(studentDTO);
		}
		return studentDTOs;
	}

}
