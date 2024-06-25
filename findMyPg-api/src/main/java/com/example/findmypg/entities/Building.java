package com.example.findmypg.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "building")
@Getter @Setter
public class Building extends BaseEntity {

 

    @Column(name = "pg_name")
    private String pgName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonBackReference
    private Owner owner;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "pg_type")
    private String pgType;
    
    @Column(name = "num_of_floors")
    private int numberofFloors;
    
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Floor> floors;
}
