package com.example.findmypg.building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.findmypg.entities.Building;
@Repository
public interface BuildingRepositry extends JpaRepository<Building, Long> {

}
