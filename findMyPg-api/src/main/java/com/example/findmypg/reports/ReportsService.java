package com.example.findmypg.reports;

//import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findmypg.RazorPayment.StudentPaymentRepositry;
import com.example.findmypg.building.BuildingRepositry;
import com.example.findmypg.configuration.ConfigurationService;
import com.example.findmypg.entities.Building;
import com.example.findmypg.entities.Owner;
import com.example.findmypg.entities.Room;
import com.example.findmypg.entities.Student;
import com.example.findmypg.entities.StudentPayment;
import com.example.findmypg.entities.StudentRoomDetails;
import com.example.findmypg.owner.OwnerRegistrationRepo;
import com.example.findmypg.room.RoomRepositry;
import com.example.findmypg.students.StudentDTO;
import com.example.findmypg.students.StudentRepositry;

@Service
public class ReportsService {

	@Autowired
	private StudentRepositry studentRepositry;

	@Autowired
	private OwnerRegistrationRepo ownerRegistrationRepo;

	@Autowired
	private RoomRepositry roomRepositry;

	@Autowired
	private BuildingRepositry buildingRepositry;

	@Autowired
	private StudentPaymentRepositry studentPaymentRepositry;

//	@Autowired
//	private ConfigurationService configurationService;

	public List<StudentDTO> getAllStudentsReports(Long ownerId) {
		Optional<Owner> owner = ownerRegistrationRepo.findById(ownerId);
		List<StudentDTO> listofReportsDTO = new ArrayList<StudentDTO>();
		List<Student> byOwner = studentRepositry.findByOwner(owner.get());
		if (owner != null && byOwner!=null) {
			for (Student student : byOwner) {
				StudentDTO newStudentDTO = new StudentDTO();
				newStudentDTO.setFirstName(student.getStudFirstName());
				newStudentDTO.setLastName(student.getStudLastName());
				newStudentDTO.setEmailId(student.getStudEmailId());
				newStudentDTO.setMobileNumber(student.getStudMobileNumber());
				newStudentDTO.setJoiningDate(student.getJoiningDate());

//				Date joiningDate = student.getJoiningDate();
				StudentPayment studentLatestPaymentDetails = studentPaymentRepositry
						.getLatestPaymentDetails(student.getStudMobileNumber());
				if (studentLatestPaymentDetails != null) {
					String nextPaymentDueDate = studentLatestPaymentDetails.getNextPaymentDueDate();
					System.out.println("Next payment date " + nextPaymentDueDate);
					// Parse the next payment due date as a LocalDate (only date part, no time)
					LocalDate nxtDate = LocalDate.parse(nextPaymentDueDate, DateTimeFormatter.ISO_LOCAL_DATE);
					LocalDate currentDate = LocalDate.now();
					// Compare the month and year of nxtDate with currentDate
					if (nxtDate.getMonth() == currentDate.getMonth() && nxtDate.getYear() == currentDate.getYear()) {
						System.out.println("The next payment due date is in the current month.");
						newStudentDTO.setNextPaymentDate(nextPaymentDueDate);
						newStudentDTO.setStatus("Paid");
					} else {
						newStudentDTO.setNextPaymentDate(nextPaymentDueDate);
						newStudentDTO.setStatus("Pending");
					}
				}

				newStudentDTO.setIdType(student.getIdType());
				byte[] studentImage = student.getStudentImage();
				if (studentImage != null) {
					String base64Image = Base64.getEncoder().encodeToString(studentImage);
					newStudentDTO.setStudentImage(base64Image);
				}
				StudentRoomDetails studentRoomDetails = student.getStudentRoomDetails();
				if (studentRoomDetails != null) {
					Optional<Building> studentBuilding = buildingRepositry.findById(studentRoomDetails.getBuildigId());
					studentBuilding.ifPresent(studentBuld -> {
						newStudentDTO.setBuildingName(studentBuld.getPgName());
					});
					newStudentDTO.setBuildingId(studentRoomDetails.getBuildigId());
					newStudentDTO.setFloorId(studentRoomDetails.getFloorId());
					Optional<Room> studentRoom = roomRepositry.findById(studentRoomDetails.getRoomId());
					studentRoom.ifPresent(studentRm -> {
						newStudentDTO.setRoomNumber(studentRm.getRoomNumber());
						newStudentDTO.setAmount(studentRm.getRates());
					});
					newStudentDTO.setRoomId(studentRoomDetails.getRoomId());
				}
//				Optional<StudentPayment> studentPayment = studentPaymentRepositry.findById(student.getId());
//				studentPayment.ifPresent(studPay -> {
//					String configValue = configurationService.getConfigValue(ownerId, "student_due");
//					int extraNumDaystoPay = Integer.parseInt(configValue);
//					if (studPay.getId() == null) {
//						System.out.println("Student have no payment till now");
//						LocalDate localJoiningDate = joiningDate.toLocalDate();
//						LocalDate paymentDueDate = localJoiningDate.plusMonths(1);
//						LocalDate plusDays = paymentDueDate.plusDays(extraNumDaystoPay);
//						newStudentDTO.setNextPaymentDate(plusDays + "");
//					} else {
//						
//						System.out.println("Student has payments ");
//					}
//
//				});
				listofReportsDTO.add(newStudentDTO);
			}

		}
		return listofReportsDTO;

	}

	public String isStudentPaidAmount(Student student) {

		return null;
	}

}
