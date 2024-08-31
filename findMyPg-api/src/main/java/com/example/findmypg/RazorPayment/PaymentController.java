
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
            @RequestParam double amount,
            @RequestParam String currency,
            @RequestParam String receipt,
            @RequestParam String mobileNumber,
            @RequestParam long userId,
            @RequestParam String paymentDate) {
        try {
        	System.out.println(mobileNumber+" Mobile Number");
        	System.out.println(paymentDate +" Payment Date");
        	System.out.println(userId+" UserId");
        	
            Order order = paymentService.createOrder(amount, currency, receipt,mobileNumber,userId,paymentDate);
            
            // Convert Order to OrderResponse
            OrderResponseDTO orderResponse = new OrderResponseDTO();
            orderResponse.setId(order.get("id"));
            orderResponse.setCurrency(order.get("currency"));
            orderResponse.setAmount(order.get("amount"));
            orderResponse.setReceipt(order.get("receipt"));
            orderResponse.setStatus(order.get("status"));
            
            

            String jsonOrder = objectMapper.writeValueAsString(orderResponse);
            
            System.out.println(jsonOrder +" Payment success ");
            return ResponseEntity.ok(jsonOrder);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @PostMapping(value = "/addPayment")
    public boolean addPayment(@RequestBody OrderResponseDTO orderResponseDTO)
    {
    	
    	return  paymentService.addPayment(orderResponseDTO);
    }

}
