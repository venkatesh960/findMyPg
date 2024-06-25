package com.example.findmypg.entities;

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

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id", nullable = false)
    private Floor floorId;  // This property name should match the 'mappedBy' in Floor

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "share_type") // 1, 2, 3, 4, 5, 6, 10 shares
    private int shareType;

    @Column(name = "rates")
    private int rates;
}
