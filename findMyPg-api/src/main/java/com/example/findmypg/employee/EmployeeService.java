package com.example.findmypg.employee;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

	public int addEmployee(EmployeeDTO empDTO) {
		Optional<Owner> owner = ownerRegistrationRepo.findById(empDTO.getId());
		Employee existingEmployee = employeeRepositry.findByOwnerAndEmpMobileNumber(owner, empDTO.getMobileNumber());
		if (owner.isPresent()) {
			if (existingEmployee == null) {
				Owner owner1 = owner.get();
				Employee employee = new Employee();
				System.out.println(empDTO + " Employee DTO");
				employee.setEmpFirstName(empDTO.getFirstName());
				employee.setEmpLastName(empDTO.getLastName());
				employee.setEmpMiddleName(empDTO.getMiddleName());
				employee.setEmpEmailId(empDTO.getEmailId());
				employee.setEmpUsername(empDTO.getUserName());
				employee.setEmpMobileNumber(empDTO.getMobileNumber());
				employee.setSalary(empDTO.getSalary());
				employee.setOwner(owner1);
				LocalDateTime dateAndTime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String formattedDateAndTime = dateAndTime.format(formatter);
				employee.setCreatedTimeStamp(formattedDateAndTime);

				System.err.println("Employee " + employee);

				Employee save = employeeRepositry.save(employee);
				if (save != null) {
					return 0; // success
				}
			}
			return 1; // mobile number already exists 
		}
		return 2; // Invalid Owner  
	}

	public List<EmployeeDTO> getAllEmployees(Long ownerId) {
		List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
		Optional<Owner> owner = ownerRegistrationRepo.findById(ownerId);
		if (owner.isPresent()) {
			List<Employee> lisofEmployees = employeeRepositry.findByOwner(owner);
			for (Employee employee : lisofEmployees) {
				EmployeeDTO employeeDTO = new EmployeeDTO();
				employeeDTO.setId(employee.getId());
				employeeDTO.setEmailId(employee.getEmpEmailId());
				employeeDTO.setFirstName(employee.getEmpFirstName());
				employeeDTO.setLastName(employee.getEmpLastName());
				employeeDTO.setMiddleName(employee.getEmpLastName());
				employeeDTO.setJoingDate(employee.getCreatedTimeStamp());
				employeeDTO.setMobileNumber(employee.getEmpMobileNumber());
				employeeDTO.setSalary(employee.getSalary());
				employeeDTOs.add(employeeDTO);
			}

		}
		return employeeDTOs;
	}

	public int removeEmployee(Long employeeId) {
		Optional<Employee> employee = employeeRepositry.findById(employeeId);
		if (employee.isPresent()) {
			employeeRepositry.delete(employee.get());
			return 0;
		}
		return 1;
	}

	public int updateEmployee(EmployeeDTO employeeDTO) {
		
		return 0;
	}

}
