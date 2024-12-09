package com.example.findmypg.reports;

import com.example.findmypg.entities.Student;
import com.example.findmypg.entities.StudentRoomDetails;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReportsDTO {
	
	private Student student;
	private StudentRoomDetails studentRoomDetails;

}
