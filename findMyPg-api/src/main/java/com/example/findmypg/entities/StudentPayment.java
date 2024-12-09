package com.example.findmypg.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "student_payments")
@Getter @Setter
public class StudentPayment extends BaseEntity {
	
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	@JsonBackReference // Back reference
	private Student student;
	
	@Column(name = "receipt")
	private String receipt;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "mobile_number")
	private String mobileNumber;
	
	@Column(name = "order_id")
	private String orderId;
	
	@Column(name = "payment_date")
	private String paymentDate;
	
	@Column(name = "payment_id")
	private String paymentId;
	
	@Column(name = "signature")
	private String signature;

	@Column(name = "currency_type")
	private String currencyType;
	
	@Column(name = "next_payment_due_date")
	private String nextPaymentDueDate;
	
}
