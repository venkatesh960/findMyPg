package com.example.findmypg.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	    private String roomNumber;

	    @Column(name = "share_type") // 1, 2, 3, 4, 5, 6, 10 shares
	    private int shareType;

	    @Column(name = "rates")
	    private int rates;
	}