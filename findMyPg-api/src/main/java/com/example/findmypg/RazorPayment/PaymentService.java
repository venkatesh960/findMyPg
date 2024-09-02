package com.example.findmypg.RazorPayment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findmypg.entities.Payments;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PaymentService {

	private final RazorpayClient razorpayClient;

	@Autowired
	public PaymentService(RazorpayClient razorpayClient) {
		this.razorpayClient = razorpayClient;
	}

	@Autowired
	private PaymentRepositry paymentRepositry;

	// Method to create an order
	public Order createOrder(int amount, String currency, String receipt)
			throws Exception {
		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", (amount * 100)); // Amount in paise
		orderRequest.put("currency", currency);
		orderRequest.put("receipt", receipt);
		System.out.println(orderRequest + " Order request");

		Order order = razorpayClient.orders.create(orderRequest);
		if (order != null) {

//			Payments payments = new Payments();
//			int amount1=order.get("amount");
//			payments.setOrderId(order.get("id"));
//			payments.setReceiptNumber(order.get("receipt"));
//			payments.setCurrency(order.get("currency"));
//			payments.setStatus(order.get("status"));
//			
//			payments.setAmount(amount1/100);
//			payments.setPaymentDate(paymentDate);
//			payments.setMobileNumber(mobileNumber);
//			payments.setUserId(userId);
//			
//			LocalDateTime dateAndTime = LocalDateTime.now();
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//	        String formattedDateAndTime = dateAndTime.format(formatter);
//	        payments.setCreatedTimeStamp(formattedDateAndTime);
//			Payments save = paymentRepositry.save(payments);
//			if (save != null) {

				return order;
//			}

		}
		return null;

	}

	public boolean addPayment(OrderResponseDTO orderResponseDTO) {

		System.out.println(orderResponseDTO.toString());
		return false;
	}

}
