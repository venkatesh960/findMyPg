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
	
	public Boolean addStudents(StudentDTO studentDTO) {
		
		Optional<Owner> ownerDetails = ownerRegistrationRepo.findById(studentDTO.getId());
		if (ownerDetails.isPresent()) {
			
			Student student=new Student();
			student.setStudfirstname(studentDTO.getFirstName());
			student.setStudlastname(studentDTO.getLastName());
			student.setStudmiddlename(studentDTO.getMiddleName());
			student.setStudusername(studentDTO.getUserName());
			student.setStudemailid(studentDTO.getEmailId());
			student.setStudmobilenumber(studentDTO.getMobileNumber());
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
