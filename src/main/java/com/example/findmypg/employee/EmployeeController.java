package com.example.findmypg.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/findmypg/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping(value="/addEmployee")
	private boolean addEmployee(@RequestBody EmployeeDTO  empDTO)
	{
		return employeeService.addEmployee(empDTO);
	}

}
