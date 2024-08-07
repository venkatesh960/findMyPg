package com.example.findmypg.RazorPayment;

import com.razorpay.RazorpayClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfig {

    @Bean
    public RazorpayClient razorpayClient() throws Exception {
       
        final String keyId ="rzp_test_1gmkkQ36LK6RTm";
        final String keySecret = "NEsYfUB0EjTAl6OBNjHgYuCj";
        return new RazorpayClient(keyId, keySecret);
    }
}
