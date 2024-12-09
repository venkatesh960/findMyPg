package com.example.findmypg.roomAmenities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.findmypg.entities.RoomAmenities;

@Repository
public interface RoomAmenitiesRepositry extends JpaRepository<RoomAmenities, Long> {

	
}
