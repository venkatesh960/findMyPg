package com.example.findmypg.employee;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findmypg.entities.Employee;
import com.example.findmypg.entities.Owner;
import com.example.findmypg.owner.OwnerRegistrationRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepositry employeeRepositry;
	
	@Autowired 
	private OwnerRegistrationRepo ownerRegistrationRepo;

	public Employee addEmployee(Long id,EmployeeDTO empDTO) {
		
		Optional<Owner> owner = ownerRegistrationRepo.findById(id);
		if(owner.isPresent()){
			Employee employee = new Employee();
			System.out.println(empDTO);
			employee.setEmpFirstName(empDTO.getEmpfirstName());
			employee.setEmpLastName(empDTO.getEmpLastName());
			employee.setEmpMiddleName(empDTO.getEmpMiddleName());
			employee.setEmpEmailId(empDTO.getEmpEmailId());
			employee.setEmpUsername(empDTO.getEmpUserName());
			employee.setEmpMobileNumber(empDTO.getEmpMobileNumber());
			employee.setOwner(owner.get());
			LocalDateTime dateAndTime=LocalDateTime.now();
			employee.setCreatedTimeStamp(dateAndTime);
			return employeeRepositry.save(employee);
			
		}
		return null;
	}

}
