package com.example.findmypg.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findmypg.entities.Student;

@Service
public class StudentService {

	@Autowired private StudentRepositry repositry;
	public Boolean addStudents(StudentDTO studentDTO) {
		Student student=new Student();
		
		student.setStudfirstname(studentDTO.getStudfirstName());
		student.setStudlastname(studentDTO.getStudLastName());
		student.setStudmiddlename(studentDTO.getStudMiddleName());
		student.setStudusername(studentDTO.getStudUserName());
		student.setOwnerid(studentDTO.getOwnerid());
		student.setStudemailid(studentDTO.getStudEmailId());
		student.setStudmobilenumber(studentDTO.getStudMobileNumber());
	
		Student check=repositry.save(student);
		if (check!=null) {
			return true;
		}
		return false;
	}

}
