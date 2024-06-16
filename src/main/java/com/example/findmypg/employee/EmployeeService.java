package com.example.findmypg.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findmypg.entities.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepositry employeeRepositry;

	public boolean addEmployee(EmployeeDTO empDTO) {
		Employee employee = new Employee();
		System.out.println(empDTO);
		employee.setEmpFirstName(empDTO.getEmpfirstName());
		employee.setEmpLastName(empDTO.getEmpLastName());
		employee.setEmpMiddleName(empDTO.getEmpMiddleName());
		employee.setEmpEmailId(empDTO.getEmpEmailId());
		employee.setEmpUsername(empDTO.getEmpUserName());
		employee.setEmpMobileNumber(empDTO.getEmpMobileNumber());
		employee.setOwnerid(empDTO.getOwnerid());
		Employee check=employeeRepositry.save(employee);
		if (check!=null) {
			return true;
		}
		return false;
	}

}
