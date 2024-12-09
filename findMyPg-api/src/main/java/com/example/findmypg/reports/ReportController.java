package com.example.findmypg.reports;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.findmypg.students.StudentDTO;

@RestController
@RequestMapping(value = "api/findmypg/reports")
public class ReportController {
	
	@Autowired ReportsService reportsService;
	
	@GetMapping(value = "/getAllStudentReports")
	private List<StudentDTO> getAllStudentsReports(@RequestParam Long ownerId){
		return reportsService.getAllStudentsReports(ownerId);
	}

}
