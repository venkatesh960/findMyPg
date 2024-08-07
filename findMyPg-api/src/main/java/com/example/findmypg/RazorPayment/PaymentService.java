package com.example.findmypg.RazorPayment;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PaymentService {

    private final RazorpayClient razorpayClient;

    @Autowired
    public PaymentService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    // Method to create an order
    public Order createOrder(double amount, String currency, String receipt) throws Exception {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", (int) (amount * 100)); // Amount in paise
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", receipt);

        return razorpayClient.orders.create(orderRequest);
    }

    // Other payment-related methods can be added here
}
