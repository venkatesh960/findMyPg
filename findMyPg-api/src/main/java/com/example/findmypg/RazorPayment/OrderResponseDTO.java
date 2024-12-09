package com.example.findmypg.RazorPayment;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderResponseDTO {
	private String paymentId;
    private String orderId;
    private String signature;
    private int amount;
    private String currency;
    private long userId;
    private String mobileNumber;
    private String paymentDate;
    private String status;
    
    private String id;
    private String receipt;
    
}
