package com.example.findmypg.floor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.findmypg.entities.Floor;

public interface FloorRepositry extends JpaRepository<Floor, Long>{

	List<Floor> findByBuilding_Id(Long buildingId);

}
