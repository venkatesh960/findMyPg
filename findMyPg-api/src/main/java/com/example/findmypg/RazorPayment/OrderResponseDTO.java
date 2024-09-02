package com.example.findmypg.RazorPayment;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderResponseDTO {
	private String mobileNumber;
    private String id;
    private String currency;
    private int amount;
    private String receipt;
    private String status; // Add any other fields you need
}
