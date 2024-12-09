package com.example.findmypg.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "room")
@Getter @Setter 
public class Room extends BaseEntity {


	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "floor_id", nullable = false)
	    @JsonBackReference
	    private Floor floorId;

	    @Column(name = "room_number")
	    private int roomNumber;

	    @Column(name = "share_type") // 1, 2, 3, 4, 5, 6, 10 shares
	    private int shareType;

	    @Column(name = "rates")
	    private int rates;
	    
	    @Column(name = "building_id")
	    private Long buildingId;
	    
	    @Column(name = "status")
	    private String status;
	    
	    @Column(name = "avilable_room")
	    private int availableRooms;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "room_amenity_id",referencedColumnName = "id")
	    @JsonManagedReference
	    private RoomAmenities roomAmenities;
	}