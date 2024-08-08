package com.example.findmypg.sms;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SMSDTO {
	
	private String to;
	private String from;
	private String subject;
	private String textMessage;
	private String pgName;

}
