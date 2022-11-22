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

import com.course.selection.app.entity.UniversityStaffMember;
import com.course.selection.app.service.UniversityStaffMemberService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UniversityStaffMemberController {

	@Autowired
	private UniversityStaffMemberService staffService;

	@GetMapping("/staffs")
	public ResponseEntity<List<UniversityStaffMember>> viewAllStaffs() {
		List<UniversityStaffMember> staff = staffService.viewAllStaffs();
		ResponseEntity<List<UniversityStaffMember>> response = new ResponseEntity<>(staff, HttpStatus.OK);
		return response;
	}

	@GetMapping("/staffs/{staff_id}")
	public ResponseEntity<UniversityStaffMember> viewStaff(@PathVariable("staff_id") @Valid Integer staffId) {
		UniversityStaffMember staff = staffService.viewStaff(staffId);
		ResponseEntity<UniversityStaffMember> response = new ResponseEntity<UniversityStaffMember>(staff,
				HttpStatus.OK);
		return response;
	}
		

	@DeleteMapping("/staffs/{staff_id}")
	public ResponseEntity<String> removeStaff(@PathVariable("staff_id") @Valid Integer staffId) {
		staffService.removeStaff(staffId);
		ResponseEntity<String> response = new ResponseEntity<String>("Staff rec deleted", HttpStatus.NO_CONTENT);
		return response;
	}

	@PutMapping("/staffs/{staff_id}")
	public ResponseEntity<UniversityStaffMember> updateStaff(@RequestBody @Valid UniversityStaffMember staff,
			@PathVariable("staff_id") Integer staffId) {
		UniversityStaffMember updatedstaf = staffService.updateStaff(staff, staffId);
		ResponseEntity<UniversityStaffMember> reponse = new ResponseEntity<UniversityStaffMember>(updatedstaf,
				HttpStatus.OK);
		return reponse;
	}

	@PostMapping("/staffs")
	public ResponseEntity<UniversityStaffMember> addStaff(@RequestBody @Valid UniversityStaffMember staff) {
		UniversityStaffMember newstaff = staffService.addStaff(staff);
		ResponseEntity<UniversityStaffMember> response = new ResponseEntity<UniversityStaffMember>(newstaff,
				HttpStatus.CREATED);
		return response;
	}

}
