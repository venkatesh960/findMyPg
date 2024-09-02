package com.example.findmypg.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter @Setter
public class Payments extends BaseEntity {

	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "mobile_number")
	private String mobileNumber;
	
//	@Column(name = "mode")
//	private String modeofPayment;
	
	@Column(name = "orderId")
	private String orderId;
	
	@Column(name = "payment_date")
	private String paymentDate;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "amount")
	private Integer amount;
	
	@Column(name = "receipt")
	private String receiptNumber;
	
	
	
}
