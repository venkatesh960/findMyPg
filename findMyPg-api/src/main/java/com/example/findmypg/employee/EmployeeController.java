package com.example.findmypg.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/findmypg/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/addemployee")
	public int addEmployeeToOwner(@RequestBody EmployeeDTO employee) {
		System.out.println(employee);
		return employeeService.addEmployee(employee);
	}
	
	@GetMapping(value = "/getAllEmployee")
	private List<EmployeeDTO> getAllEmployees(@RequestParam Long ownerId){
		return employeeService.getAllEmployees(ownerId);
	}
	
	@DeleteMapping(value = "/removeEmployee")
	private int removeEmployee(@RequestParam Long employeeId) {
		return employeeService.removeEmployee(employeeId);
	}
	
	@PutMapping(value = "/updateEmployee")
	private int updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
		return employeeService.updateEmployee(employeeDTO);
	}

}
