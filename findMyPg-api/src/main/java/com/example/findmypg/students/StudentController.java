package com.example.findmypg.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/findmypg/student")
public class StudentController {
	
	@Autowired private StudentService studentService;

	@PostMapping(value = "/addStudent")
	private  boolean addStudent(@RequestBody StudentDTO dto)
	{
		return studentService.addStudents(dto);
	}

}
