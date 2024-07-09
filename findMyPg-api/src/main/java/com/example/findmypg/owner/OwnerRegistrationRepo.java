package com.example.findmypg.owner;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.findmypg.entities.Owner;

@Repository
public interface OwnerRegistrationRepo extends JpaRepository<Owner, Long> {

    Owner findByMobileNum(String mobileNumber);

    Owner findByMobileNumAndUserName(String mobileNumber, String userName);

    Owner findByMobileNumAndPassword(String mobileNumber, String password);

    
    @Query(value= " SELECT distinct(*) FROM Owner ow, Building bu, Floor fl, Room ro WHERE ow.id = bu.owner_id AND bu.id = fl.building_id AND fl.id = ro.floor_id AND ow.id =  :ownerId ",nativeQuery = true)
    List<Owner> getMyDetails(@Param("ownerId") String ownerId);
    
//    SELECT bu.pg_name, bu.pg_type, bu.num_of_floors, bu.location, fl.floor, fl.numberOfRooms, ro.roomNumber, ro.shareType, ro.rates, ro.floorId 
//    FROM Owner ow, Building bu, Floor fl, Room ro 
//    WHERE ow.id = bu.ownerId AND bu.id = fl.buildingId AND fl.id = ro.floorId AND ow.id = 1;
    
}
