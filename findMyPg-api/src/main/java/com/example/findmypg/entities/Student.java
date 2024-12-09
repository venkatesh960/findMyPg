package com.example.findmypg.entities;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "student")
@Getter
@Setter
public class Student extends BaseEntity {

	@Column(name = "first_name")
	private String studFirstName;

	@Column(name = "last_name")
	private String studLastName;

	@Column(name = "middle_name")
	private String studmiddlename;

	@Column(name = "email_id")
	private String studEmailId;

	@Column(name = "mobile_number")
	private String studMobileNumber;

	@Column(name = "joining_date")
	private Date joiningDate;

	@Column(name = "id_type")
	private String idType;

	@Column(name = "id_number")
	private long idNumber;

	@Lob
	@Column(name = "student_image")
	private byte[] studentImage;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_room_details_id", referencedColumnName = "id")
	@JsonManagedReference // Forward reference
	private StudentRoomDetails studentRoomDetails;

	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	@JsonBackReference
	private Owner owner;
	
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<StudentPayment> studentPayments;
}
