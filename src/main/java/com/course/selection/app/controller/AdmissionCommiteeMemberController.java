package com.course.selection.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.selection.app.entity.Admission;
import com.course.selection.app.entity.AdmissionCommiteeMember;
import com.course.selection.app.service.AdmissionCommiteeMemberService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class AdmissionCommiteeMemberController {

	@Autowired
	private AdmissionCommiteeMemberService admissionCommiteeMemberService;

	/**
	 * Creating table and save to database http://localhost:8080/api/v1/admins
	 */
	@PostMapping("/admins")
	public ResponseEntity<AdmissionCommiteeMember> saveAdmin(@RequestBody @Valid AdmissionCommiteeMember admin) {
		AdmissionCommiteeMember newAdmin = admissionCommiteeMemberService.addCommiteeMember(admin);
		ResponseEntity<AdmissionCommiteeMember> response = new ResponseEntity<AdmissionCommiteeMember>(newAdmin,
				HttpStatus.CREATED);
		return response;
	}

	// Updating admin details and save to database
	// http://localhost:8080/api/v1/admins/15
	@PutMapping("/admins/{admin_id}")
	public ResponseEntity<AdmissionCommiteeMember> updateAdmin(
			@RequestBody @Valid AdmissionCommiteeMember admissionCommiteeMember,
			@PathVariable("admin_id") @Valid Integer adminId) {
		AdmissionCommiteeMember updatedAdmin = admissionCommiteeMemberService
				.updateCommiteeMember(admissionCommiteeMember, adminId);
		ResponseEntity<AdmissionCommiteeMember> response = new ResponseEntity<AdmissionCommiteeMember>(updatedAdmin,
				HttpStatus.OK);
		return response;
	}

	// Getting all admin details from database
	// http://localhost:8080/api/v1/applicants
	@GetMapping("/admins")
	public ResponseEntity<List<AdmissionCommiteeMember>> viewAllAdmins() {
		List<AdmissionCommiteeMember> viewAllAdmins = admissionCommiteeMemberService.viewAllCommiteeMembers();
		ResponseEntity<List<AdmissionCommiteeMember>> response = new ResponseEntity<>(viewAllAdmins, HttpStatus.OK);
		return response;
	}

	// will not work if Id is not present in DB
	// Getting particular admin details from database
	// http://localhost:8080/api/v1/applicants/15
	@GetMapping("/admins/{admin_id}")
	public ResponseEntity<AdmissionCommiteeMember> viewAdmins(@PathVariable("admin_id") @Valid Integer adminId) {
		AdmissionCommiteeMember viewAdmin = admissionCommiteeMemberService.viewCommiteeMember(adminId);
		ResponseEntity<AdmissionCommiteeMember> response = new ResponseEntity<AdmissionCommiteeMember>(viewAdmin,
				HttpStatus.OK);
		return response;
	}

	@DeleteMapping("/admins/{admin_id}")
	public ResponseEntity<String> deleteAdmin(@PathVariable("admin_id") @Valid Integer applicantId) {
		admissionCommiteeMemberService.removeCommiteeMember(applicantId);
		ResponseEntity<String> response = new ResponseEntity<String>("Successfully Deleted", HttpStatus.NO_CONTENT);
		return response;
	}

	// THIS IS FOR UPDATING STATUS
	@PutMapping("/admins/admissions/{applicant_id}")
	public ResponseEntity<String> updateAdmissionStatus(@PathVariable("applicant_id") @Valid Long applicantId,
			@RequestBody @Valid Admission admission) {

		admissionCommiteeMemberService.provideAdmissionResult(applicantId, admission);
		ResponseEntity<String> response = new ResponseEntity<String>("Successfully Admission Status Updated",
				HttpStatus.OK);
		return response;
	}

}
