package com.course.selection.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.course.selection.app.constants.AdmissionStatus;
import com.course.selection.app.entity.Admission;
import com.course.selection.app.entity.Applicant;
import com.course.selection.app.service.ApplicantService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class ApplicantController {
	
	@Autowired
	ApplicantService applicantService;
	
	//Save applicant details in database
	//http://localhost:8080/api/v1/applicants
	@PostMapping("/applicants")
	public ResponseEntity<Applicant> saveApplicant(@RequestBody @Valid Applicant applicant){
		Applicant newApplicant = applicantService.saveApplicantDetails(applicant);
		ResponseEntity<Applicant> response = new ResponseEntity<Applicant>(newApplicant, HttpStatus.CREATED);
		return response;
	}
	
	//Update applicant details and save to database
	//http://localhost:8080/api/v1/applicants/15
	@PutMapping("/applicants/{applicant_id}")
	public ResponseEntity<Applicant> updateApplicant(@RequestBody @Valid Applicant applicant, @PathVariable("applicant_id") Long applicantId){
		Applicant updatedApplicant = applicantService.updateApplicantDetails(applicant, applicantId);
		ResponseEntity<Applicant> reponse = new ResponseEntity<Applicant>(updatedApplicant, HttpStatus.OK);
		return reponse;
	}
	
	//Get all applicant details from database
	//http://localhost:8080/api/v1/applicants
	@GetMapping("/applicants")
	public ResponseEntity<List<Applicant>> getAllApplicants() {
		List<Applicant> applicants = applicantService.getAllApplicantDetails();
		ResponseEntity<List<Applicant>> response = new ResponseEntity<>(applicants, HttpStatus.OK);
		return response;
	}
	
	//will not work if Id is not present in DB
	//Get particular applicant details from database
	//http://localhost:8080/api/v1/applicants/15
	@GetMapping("/applicants/{applicant_id}")
	public ResponseEntity<Applicant> getApplicants(@PathVariable("applicant_id") @Valid Long applicantId) {
		Applicant applicant = applicantService.getApplicantDetails(applicantId);
		ResponseEntity<Applicant> response = new ResponseEntity<Applicant>(applicant, HttpStatus.OK);
		return response;
	}
	
	//Delete particular applicant details in database
	//http://localhost:8080/api/v1/applicants/16
	@DeleteMapping("/applicants/{applicant_id}")
	public ResponseEntity<String> deleteApplicant(@PathVariable("applicant_id") @Valid Long applicantId){
		applicantService.deleteApplicantDetails(applicantId);
		ResponseEntity<String> response = new ResponseEntity<String>("Successfully Deleted", HttpStatus.NO_CONTENT);
		return response;
		
	}
	
}
