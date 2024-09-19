package com.example.findmypg.students;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.findmypg.entities.Building;
import com.example.findmypg.entities.Student;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/api/findmypg/student")
public class StudentController {
	
	@Autowired private StudentService studentService;

	@PostMapping(value = "/addStudent")
	private  boolean addStudent(@RequestBody StudentDTO dto)
	{
		return studentService.addStudents(dto);
	}
	
	@PostMapping(value = "/addStudentWithImage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	private  ResponseEntity<Student> addStudentWithImage(@RequestPart("studentDTO") String studentDTOString, @RequestPart("image") MultipartFile image) throws IOException
	{
		ObjectMapper objectMapper=new ObjectMapper();
		StudentDTO studentDTO=objectMapper.readValue(studentDTOString, StudentDTO.class);
		Student studentsWithImage = studentService.addStudentsWithImage(studentDTO,image);
		return new ResponseEntity<>(studentsWithImage,HttpStatus.CREATED);
		
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
	@PutMapping(value = "/vacantStudent")
	private Boolean vacantStudent(@RequestBody StudentDTO studentDTO) {
	
		return studentService.vacantStudent(studentDTO);
	}

}
