package com.example.findmypg.RazorPayment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.findmypg.entities.Payments;

public interface PaymentRepositry extends JpaRepository<Payments, Long> {

}
