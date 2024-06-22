package com.example.findmypg.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.findmypg.entities.Employee;


@RestController
@RequestMapping("/api/findmypg/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	 @PostMapping("/{ownerId}/employees")
	    public Employee addEmployeeToOwner(@PathVariable Long ownerId, @RequestBody EmployeeDTO employee) {
		 
	        return employeeService.addEmployee(ownerId, employee);
	    }

}
