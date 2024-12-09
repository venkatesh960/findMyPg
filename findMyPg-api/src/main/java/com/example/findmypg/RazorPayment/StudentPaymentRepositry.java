package com.example.findmypg.RazorPayment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.findmypg.entities.StudentPayment;

public interface StudentPaymentRepositry extends JpaRepository<StudentPayment, Long> {

	List<StudentPayment> findByMobileNumber(String mobileNumber);
	
	@Query(value = " SELECT * FROM findmypg.student_payments where mobile_number = :mobileNumber order by payment_date desc LIMIT 1 ",nativeQuery = true)
	StudentPayment getLatestPaymentDetails(@Param("mobileNumber") String mobileNumber);

}
