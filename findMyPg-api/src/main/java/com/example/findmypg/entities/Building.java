package com.example.findmypg.entities;

import java.util.List;
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

    private static final long serialVersionUID = 1L;

    @Column(name = "pg_name")
    private String pgName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @Column(name = "location")
    private String location;
    
    @Column(name = "pg_type")
    private String pgType;
    
    @Column(name = "num_of_floors")
    private int numberofFloors;
    
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Floor> floors;
}
