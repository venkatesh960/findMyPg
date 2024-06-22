package com.example.findmypg.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name="owner")
@Getter @Setter  
public class Owner extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "middle_name")
	private String middleName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email_id")
	private String email_Id;
	
	@Column(name = "mobile_num")
	private String mobileNum;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	 @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<Employee> employees;
	

}
