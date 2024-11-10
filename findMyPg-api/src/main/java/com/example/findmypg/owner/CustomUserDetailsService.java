//package com.example.findmypg.owner;
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.findmypg.entities.Owner;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    
//    
//    @Autowired
//    private OwnerRegistrationRepo ownerRegistrationRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
//        Owner owner = ownerRegistrationRepo.findByMobileNum(mobileNumber); // Implement this method in your service
//        if (owner == null) {
//            throw new UsernameNotFoundException("User not found with mobile number: " + mobileNumber);
//        }
//        return new org.springframework.security.core.userdetails.User(owner.getMobileNum(), owner.getPassword(), new ArrayList<>()); // Modify as needed
//    }
//}
