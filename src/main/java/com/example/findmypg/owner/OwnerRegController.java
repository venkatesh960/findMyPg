package com.example.findmypg.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/findmypg/owner")
public class OwnerRegController {

    @Autowired
    private OwnerRegService ownerRegService;

    @PostMapping(value = "/registration")
   
    public Boolean ownerRegistration(@RequestBody OwnerRegDTO ownerDTO) {
        return ownerRegService.ownerRegistration(ownerDTO);
    }

    @GetMapping(value = "/getOwnerDetails")
    public OwnerRegDTO getOwnerData(@RequestParam String mobileNumber) {
        return ownerRegService.getOwnerDetails(mobileNumber);
    }
    
    @GetMapping(value = "/onwerLoginChech")
    private boolean ownerLogin(@RequestBody OwnerRegDTO dto)
    {
    	return ownerRegService.loginChcek(dto);
    }
}
