package com.example.findmypg.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "room_amenities")
@Getter
@Setter
@Table
public class RoomAmenities extends BaseEntity {

    @Column(name = "amenity")
    private String amenity; // Fixed typo from 'amenty'

    @OneToOne(mappedBy = "roomAmenities")
    @JsonBackReference
    private Room room;
}
