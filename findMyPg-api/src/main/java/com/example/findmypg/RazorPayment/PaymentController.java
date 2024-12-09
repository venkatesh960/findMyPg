
package com.example.findmypg.RazorPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/findmypg/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = "/create-order", produces = "application/json")
    public ResponseEntity<String> createOrder(
            @RequestParam int amount,
            @RequestParam String currency,
            @RequestParam String receipt
           
            ) {
        try {

        	System.out.println(amount);
        	System.out.println(currency+" .. "+receipt);
            Order order = paymentService.createOrder(amount, currency, receipt);
            System.out.println("Iam here "+order);
            OrderResponseDTO orderResponse = new OrderResponseDTO();
            Object object = order.get("id");
            System.out.println(object);
           
            orderResponse.setId(order.get("id"));
            orderResponse.setCurrency(order.get("currency"));
            orderResponse.setAmount(order.get("amount"));
            orderResponse.setReceipt(order.get("receipt"));
            orderResponse.setStatus(order.get("status"));
            
            

            String jsonOrder = objectMapper.writeValueAsString(orderResponse);
            System.out.println("Order is create "+jsonOrder);
            return ResponseEntity.ok(jsonOrder);
        } catch (JsonProcessingException e) {
        	System.out.println("Here i got exception ");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
        	System.out.println("Herer second time i got exception "+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @PostMapping(value = "/addPayment")
    public boolean addPayment(@RequestBody OrderResponseDTO orderResponseDTO)
    {
    	return  paymentService.addPayment(orderResponseDTO);
    }

    @PostMapping(value="/verify")
    public ResponseEntity<String> verifyPayment(@RequestBody OrderResponseDTO orderResponseDTO) {
        try {
        	String orderDTOasString=objectMapper.writeValueAsString(orderResponseDTO);
        	System.out.println("Before verifying the payment s==>> "+orderDTOasString);
        	RazorpayClient razorpayClient = new RazorpayClient("rzp_test_uAD2cVyYsz6fWc", "n8DUP45BUMEPFQgC9dsvOumP");
        	System.out.println(razorpayClient);
            // Fetch the payment details using the payment ID
            Payment payment = razorpayClient.payments.fetch(orderResponseDTO.getPaymentId());
            System.out.println("payement status ==>> "+objectMapper.writeValueAsString(payment));
            System.out.println("Order status => "+payment.get("status"));
            // Check the payment status
            if ("captured".equals(payment.get("status"))) {
                // Payment was successful
                System.out.println("Payment successful: " + payment.get("status"));

                // Save the payment status and other details in your database
                // (paymentDetails.getAmount(), paymentDetails.getUserId(), etc.)

                return ResponseEntity.ok("Payment successful");
            } else {
                // Payment failed
                System.out.println("Payment failed: " + payment.get("status"));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment verification failed");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error verifying payment");
        }
    }

}
