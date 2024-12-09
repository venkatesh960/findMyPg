package com.example.findmypg.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.findmypg.entities.Employee;
import com.example.findmypg.entities.Owner;

@Repository
public interface EmployeeRepositry extends JpaRepository<Employee, Long>{

	List<Employee> findByOwner(Optional<Owner> owner);

	Employee findByOwnerAndEmpMobileNumber(Optional<Owner> owner, String mobileNumber);
	
	
}
