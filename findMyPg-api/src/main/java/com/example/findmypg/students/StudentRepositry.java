package com.example.findmypg.students;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.findmypg.entities.Student;
@Repository
public interface StudentRepositry extends JpaRepository<Student, Long> {

	Student findByStudEmailIdAndStudMobileNumber(String emailId, String mobileNumber);

	//	Student findByStudMobileNumber(String mobileNumber);

}
