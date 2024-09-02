package com.example.findmypg.students;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

import com.example.findmypg.entities.StudentRoomDetails;

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
	
	@Autowired
	private StudentRoomDetailsRepositry detailsRepositry;


	
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
			
		
			LocalDateTime localDateandTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedDateAndTime = localDateandTime.format(formatter);
	        
			student.setCreatedTimeStamp(formattedDateAndTime);

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
//			student.setStudusername(studentDTO.getUserName());

			student.setStudEmailId(studentDTO.getEmailId());
			student.setStudMobileNumber(studentDTO.getMobileNumber());
			student.setIdType(studentDTO.getIdType());
			student.setIdNumber(studentDTO.getIdNumber());
			student.setJoiningDate(studentDTO.getJoiningDate());
			
			LocalDateTime dateAndTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedDateAndTime = dateAndTime.format(formatter);
			student.setCreatedTimeStamp(formattedDateAndTime);
			student.setOwner(ownerDetails.get());
			
			Student save1=studentrepositry.save(student);
			
			StudentRoomDetails studentRoomDetails=new StudentRoomDetails();
			studentRoomDetails.setBuildigId(studentDTO.getBuildingId());
			studentRoomDetails.setFloorId(studentDTO.getFloorId());
			studentRoomDetails.setRoomId(studentDTO.getRoomId());
			studentRoomDetails.setStudent(student);
			studentRoomDetails.setStatus("IN");
			studentRoomDetails.setCreatedTimeStamp(formattedDateAndTime);
			StudentRoomDetails save2 = detailsRepositry.save(studentRoomDetails);
			
			student.setStudentRoomDetails(studentRoomDetails);
			studentrepositry.save(student);
			
			Room room2=room.get();
			int availableRoom=room2.getAvailableRooms();
			room2.setAvailableRooms(availableRoom-1);
			Room save3 = roomRepositry.save(room2);
		
			if (room2.getAvailableRooms()==0) {
				room2.setStatus("Unavailable");
				room2.setUpdatetimestamp(formattedDateAndTime);
				roomRepositry.save(room2);
				
			}
			System.err.println("Student Entitu "+student);
			if (save1!=null && save2!=null && save3!=null) {
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
			System.out.println("Building is Null "+building);
			return null;
		}
		List<StudentRoomDetails> listofStudent = detailsRepositry.findByBuildigId(building.getId());
		if (listofStudent==null) {
			System.out.println("List of students are null "+listofStudent);
			return null;
		}
		for (StudentRoomDetails studentroomDetails : listofStudent) {
			if(studentroomDetails.getStatus().equalsIgnoreCase("vacant")) {
				System.out.println("Iam here for counting for vacant "+studentroomDetails.getStatus());
				continue;
			}
			StudentDTO studentDTO=new StudentDTO();
			
			Student student=studentroomDetails.getStudent();
			studentDTO.setStatus(studentroomDetails.getStatus());
			studentDTO.setId(studentroomDetails.getId());
			
			studentDTO.setFirstName(student.getStudFirstName());
			studentDTO.setLastName(student.getStudLastName());
			studentDTO.setEmailId(student.getStudEmailId());
			studentDTO.setMobileNumber(student.getStudMobileNumber());
			studentDTO.setJoiningDate(student.getJoiningDate());
			Optional<Floor> floor = floorRepositry.findById(student.getStudentRoomDetails().getFloorId());
			if (floor.isPresent()) {
				studentDTO.setFloorNumber(floor.get().getFloorNumber());
				studentDTO.setFloorId(floor.get().getId());
			}
			Optional<Room> room = roomRepositry.findById(student.getStudentRoomDetails().getRoomId());
			if (room.isPresent()) {
				studentDTO.setRoomNumber(room.get().getRoomNumber());
				studentDTO.setRoomId(room.get().getId());
				studentDTO.setAmount(room.get().getRates());
			   studentDTOs.add(studentDTO);
		}
		}
		return studentDTOs;
	}

	public Boolean vacantStudent(StudentDTO studentDTO) {
	
		Optional<Student> student = studentrepositry.findById(studentDTO.getId());
		if (!student.isPresent()) {
			return false;
		}
		Optional<Room> room = roomRepositry.findById(student.get().getStudentRoomDetails().getRoomId());
		if (room.isEmpty()) {
			return false;
		}
		Room room2=room.get();
		room2.setAvailableRooms(room2.getAvailableRooms()+1);
		if (!room2.getStatus().equalsIgnoreCase("Available")) {
			room2.setStatus("Available");
			roomRepositry.save(room2);
		}
		Room save = roomRepositry.save(room2);
		StudentRoomDetails studentRoomDetails=student.get().getStudentRoomDetails();
		studentRoomDetails.setStatus("vacant");
		LocalDateTime dateAndTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateAndTime = dateAndTime.format(formatter);
        studentRoomDetails.setUpdatetimestamp(formattedDateAndTime);
		StudentRoomDetails save2 = detailsRepositry.save(studentRoomDetails);
		if(save!=null && save2!=null) {
			return true;
		}
		return null;
	}
}
