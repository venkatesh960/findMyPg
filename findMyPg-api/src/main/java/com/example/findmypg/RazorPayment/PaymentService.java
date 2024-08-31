package com.example.findmypg.RazorPayment;

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
	public Order createOrder(double amount, String currency, String receipt, String mobileNumber, long userId,String paymentDate)
			throws Exception {
		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", (int) (amount * 100)); // Amount in paise
		orderRequest.put("currency", currency);
		orderRequest.put("receipt", receipt);
		System.out.println(orderRequest + " Order request");

		Order order = razorpayClient.orders.create(orderRequest);
		if (order != null) {

			Payments payments = new Payments();
//			double amount1=order.get("amount");
			payments.setOrderId(order.get("id"));
			payments.setReceiptNumber(order.get("receipt"));
			payments.setCurrency(order.get("currency"));
			payments.setStatus(order.get("status"));
			
			payments.setAmount(100.98);
			payments.setPaymentDate(paymentDate);
			payments.setMobileNumber(mobileNumber);
			payments.setUserId(userId);
			
			Payments save = paymentRepositry.save(payments);
			if (save != null) {

				return order;
			}

		}
		return null;

	}

	public boolean addPayment(OrderResponseDTO orderResponseDTO) {

		System.out.println(orderResponseDTO.toString());
		return false;
	}

}
