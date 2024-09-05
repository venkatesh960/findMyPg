package com.example.findmypg.students;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.findmypg.entities.StudentRoomDetails;

public interface StudentRoomDetailsRepositry extends JpaRepository<StudentRoomDetails, Long>{
	
	List<StudentRoomDetails> findByBuildigId(Long id);

	StudentRoomDetails findByBuildigIdAndFloorIdAndRoomId(Long id, long floorId, long roomId);

	List<StudentRoomDetails> findByRoomId(Long id);


}
