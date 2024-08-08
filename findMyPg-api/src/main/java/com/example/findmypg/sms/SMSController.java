package com.example.findmypg.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/findmypg/message")
@RestController
public class SMSController {

	@Autowired
	private SMSService smsService;

	@PostMapping("/sendEmail")
	public ResponseEntity<String> sendEmail(@RequestBody SMSDTO smsdto) {
	    try {
	        // Assuming `sendEmail` method handles sending email logic
	        boolean success = smsService.sendEmail(smsdto);
	        if (success) {
	            return ResponseEntity.ok("Email sent successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}

//	@PostMapping("/sendEmail")
//	public ResponseEntity<?> sendEmail(@RequestBody SMSDTO smsdto) {
//	    // Handle the email sending logic
//	    return ResponseEntity.ok("Email sent successfully");
//	}


}
