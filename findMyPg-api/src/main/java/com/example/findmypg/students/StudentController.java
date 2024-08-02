package com.example.findmypg.students;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping(value = "/assignRoom")
	private boolean assignRoomtoStudent(@RequestBody StudentDTO dto)
	{
		return studentService.assignRoomtoStudent(dto);
	}
	
	@GetMapping(value = "/getAssignedStudent")
	private List<StudentDTO> getAllStudentAssignedtoRoom(@RequestParam String pgName){
		
		return studentService.getAllStudentAssignedRooms(pgName);
	}

}
