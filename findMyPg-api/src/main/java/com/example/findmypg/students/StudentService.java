package com.example.findmypg.students;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findmypg.entities.Owner;
import com.example.findmypg.entities.Student;
import com.example.findmypg.owner.OwnerRegistrationRepo;

@Service
public class StudentService {

	@Autowired 
	private StudentRepositry repositry;
	
	@Autowired 
	private OwnerRegistrationRepo ownerRegistrationRepo;
	
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

}
