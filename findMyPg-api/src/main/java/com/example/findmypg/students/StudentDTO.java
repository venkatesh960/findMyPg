package com.example.findmypg.students;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class StudentDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String mobileNumber;
	private String userName;
	private String emailId;
	private String idType;
	private long idNumber;
	private MultipartFile file;
	
	private long buildingId;
	private long floorId;
	private long roomId;
	private Date joiningDate;
	private String pgName;
	private int floorNumber;
	private int roomNumber;
	private String status;
	private int amount;
	
	private String studentImage;
	private String buildingName;
	private String nextPaymentDate;


}
