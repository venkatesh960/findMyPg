package com.example.findmypg.employee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

	public Boolean addEmployee(EmployeeDTO empDTO) {

		Optional<Owner> owner = ownerRegistrationRepo.findById(empDTO.getId());
		if (owner.isPresent()) {
			Owner owner1 = owner.get();
			Employee employee = new Employee();
			System.out.println(empDTO + " Employee DTO");
			employee.setEmpFirstName(empDTO.getFirstName());
			employee.setEmpLastName(empDTO.getLastName());
			employee.setEmpMiddleName(empDTO.getMiddleName());
			employee.setEmpEmailId(empDTO.getEmailId());
			employee.setEmpUsername(empDTO.getUserName());
			employee.setEmpMobileNumber(empDTO.getMobileNumber());
			employee.setOwner(owner1);
			LocalDateTime dateAndTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedDateAndTime = dateAndTime.format(formatter);
	        employee.setCreatedTimeStamp(formattedDateAndTime);
			
	        System.err.println("Employee " + employee);


			Employee save = employeeRepositry.save(employee);
			if (save != null) {
				return true;
			}
			return false;

		}
		return false;
	}

}
