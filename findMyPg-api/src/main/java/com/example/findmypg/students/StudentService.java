package com.example.findmypg.students;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findmypg.entities.Owner;
import com.example.findmypg.entities.Room;
import com.example.findmypg.entities.Student;
import com.example.findmypg.owner.OwnerRegistrationRepo;
import com.example.findmypg.room.RoomRepositry;

@Service
public class StudentService {

	@Autowired 
	private StudentRepositry repositry;
	
	@Autowired 
	private OwnerRegistrationRepo ownerRegistrationRepo;
	
	@Autowired 
	private RoomRepositry roomRepositry;
	
	public boolean addStudents(StudentDTO studentDTO) {
		
		Optional<Owner> ownerDetails = ownerRegistrationRepo.findById(studentDTO.getId());
		if (ownerDetails.isPresent()) {
			Student student2 = repositry.findByStudEmailIdAndStudMobileNumber(studentDTO.getEmailId(),studentDTO.getMobileNumber());
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
			Student check=repositry.save(student);
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
			Student student2 = repositry.findByStudEmailIdAndStudMobileNumber(studentDTO.getEmailId(),studentDTO.getMobileNumber());
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
			
			LocalDateTime dateAndTime = LocalDateTime.now();
			student.setCreatedTimeStamp(dateAndTime);
			student.setOwner(ownerDetails.get());
			Student check=repositry.save(student);
			
			Room room2=room.get();
			int availableRoom=room2.getAvailableRooms();
			room2.setAvailableRooms(availableRoom-1);
			Room save = roomRepositry.save(room2);
			
			System.err.println("Student Entitu "+student);
			if (check!=null && save!=null) {
				return true;
			}
			return false;
		}
		return false;
	}

}
