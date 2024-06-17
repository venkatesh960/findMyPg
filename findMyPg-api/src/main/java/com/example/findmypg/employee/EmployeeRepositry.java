package com.example.findmypg.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.findmypg.entities.Employee;

@Repository
public interface EmployeeRepositry extends JpaRepository<Employee, Long>{
	
	
}
