package com.example.findmypg.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "student_room_details")
@Getter @Setter
public class StudentRoomDetails extends BaseEntity {

	@OneToOne(mappedBy = "studentRoomDetails")
    private Student student;
	
	@Column(name = "building_id")
	private long buildigId;
	
	@Column(name = "floor_id")
	private long floorId;
	
	@Column(name = "room_id")
	private long roomId;
	
	@Column(name = "status")
	private String status;
	
	
}
