package com.example.findmypg.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/findmypg/message")
@RestController
public class SMSController {

	@Autowired
	private SMSService smsService;

	@PostMapping(value = "/sendEmail")
	private boolean sendEmail(@RequestBody SMSDTO smsdto) {
		return smsService.sendEmail(smsdto);
	}

}
