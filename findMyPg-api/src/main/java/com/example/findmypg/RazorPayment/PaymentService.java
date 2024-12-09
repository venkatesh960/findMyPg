package com.example.findmypg.RazorPayment;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findmypg.entities.Student;
import com.example.findmypg.entities.StudentPayment;
import com.example.findmypg.students.StudentRepositry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PaymentService {

	@Autowired
	public PaymentService(RazorpayClient razorpayClient) {
	}

	@Autowired
	private StudentRepositry studentRepositry;

	@Autowired
	private StudentPaymentRepositry studentPaymentRepositry;

	// Method to create an order
	public Order createOrder(int amount, String currency, String receipt) throws Exception {
		RazorpayClient razorpayClient = new RazorpayClient("rzp_test_uAD2cVyYsz6fWc", "n8DUP45BUMEPFQgC9dsvOumP");

		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", (amount * 100)); // Amount in paise
		orderRequest.put("currency", currency);
		orderRequest.put("receipt", receipt);
		System.out.println(orderRequest + " Order request");

		Order order = razorpayClient.orders.create(orderRequest);

		if (order != null) {
			return order;

		}
		return null;

	}

	public boolean addPayment(OrderResponseDTO orderResponseDTO) {
		System.out.println("inside addPayment()");
		try {
			System.out.println(new ObjectMapper().writeValueAsString(orderResponseDTO));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		List<StudentPayment> listofStudentsPayments = studentPaymentRepositry
				.findByMobileNumber(orderResponseDTO.getMobileNumber());
		System.out.println("Student mobile Number " + orderResponseDTO.getMobileNumber());
		System.out.println(listofStudentsPayments.size() + " List size ");
		if (listofStudentsPayments == null || listofStudentsPayments.size() == 0) {
			Student student = studentRepositry.findByStudMobileNumber(orderResponseDTO.getMobileNumber());
			try {
				System.out.println(new ObjectMapper().writeValueAsString(student));
			} catch (JsonProcessingException e) {
				System.out.println(e.getLocalizedMessage());
				e.printStackTrace();
			}
			Date joiningDate = student.getJoiningDate();

			StudentPayment newStudentPayment1 = new StudentPayment();
			newStudentPayment1.setPaymentId(orderResponseDTO.getPaymentId());
			newStudentPayment1.setOrderId(orderResponseDTO.getOrderId());
			newStudentPayment1.setSignature(orderResponseDTO.getSignature());
			newStudentPayment1.setAmount(orderResponseDTO.getAmount());
			newStudentPayment1.setCurrencyType(orderResponseDTO.getCurrency());
			newStudentPayment1.setStudent(student);
			newStudentPayment1.setMobileNumber(orderResponseDTO.getMobileNumber());
			newStudentPayment1.setPaymentDate(orderResponseDTO.getPaymentDate());
			newStudentPayment1.setStatus(orderResponseDTO.getStatus());
			LocalDate nextPaymentDate = joiningDate.toLocalDate().plusMonths(1);
			System.err.println("Joining date ==>> " + joiningDate);
			System.out.println("Next payment ==>> " + nextPaymentDate);

			newStudentPayment1.setNextPaymentDueDate(nextPaymentDate.toString());

			LocalDateTime dateAndTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedDateAndTime = dateAndTime.format(formatter);
			newStudentPayment1.setCreatedTimeStamp(formattedDateAndTime);

			StudentPayment save = studentPaymentRepositry.save(newStudentPayment1);
			if (save != null) {
				return true;
			}

		} else {
			System.out.println("Inside else condition ");
			StudentPayment latestPaymentDetails = studentPaymentRepositry
					.getLatestPaymentDetails(orderResponseDTO.getMobileNumber());
			try {
				System.out.println("Latest Payment Details\n"+new ObjectMapper().writeValueAsString(latestPaymentDetails));
			} catch (JsonProcessingException e) {
				System.out.println(e.getLocalizedMessage());
				e.printStackTrace();
			}
			StudentPayment studentPayment = new StudentPayment();
			studentPayment.setPaymentId(orderResponseDTO.getPaymentId());
			studentPayment.setOrderId(orderResponseDTO.getOrderId());
			studentPayment.setSignature(orderResponseDTO.getSignature());
			studentPayment.setAmount(orderResponseDTO.getAmount());
			studentPayment.setCurrencyType(orderResponseDTO.getCurrency());
			Optional<Student> student = studentRepositry.findById(orderResponseDTO.getUserId());
			student.ifPresent(stud -> {
				studentPayment.setStudent(stud);
			});
			studentPayment.setMobileNumber(orderResponseDTO.getMobileNumber());
			studentPayment.setPaymentDate(orderResponseDTO.getPaymentDate());
			studentPayment.setStatus(orderResponseDTO.getStatus());

			LocalDateTime dateAndTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedDateAndTime = dateAndTime.format(formatter);
			studentPayment.setCreatedTimeStamp(formattedDateAndTime);

			String paymentDate = orderResponseDTO.getPaymentDate();
			System.out.println("Payment made date " + paymentDate);
			
			String nextPaymentDueDate = latestPaymentDetails.getNextPaymentDueDate();
			System.out.println("next payment due date ==>> "+nextPaymentDueDate);
			
			// Parse the next payment due date as a LocalDate (only date part, no time)
		    LocalDate date = LocalDate.parse(nextPaymentDueDate, DateTimeFormatter.ISO_LOCAL_DATE);

		    // Add one month to the LocalDate
		    LocalDate newDate = date.plusMonths(1);

		    // Format the updated date (without time)
		    String updatedNextPaymentDueDate = newDate.format(DateTimeFormatter.ISO_LOCAL_DATE); // Use the correct format (yyyy-MM-dd)
		    studentPayment.setNextPaymentDueDate(updatedNextPaymentDueDate);

		    // Log the original and updated due dates
		    System.out.println("Original Date: " + nextPaymentDueDate);
		    System.out.println("Updated Date: " + updatedNextPaymentDueDate);

		    // Save the payment record
		    StudentPayment save = studentPaymentRepositry.save(studentPayment);
		    if (save != null) {
		        return true;
		    }
		    return false;
		}
		return false;
	}

}
