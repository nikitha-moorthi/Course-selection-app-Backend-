package com.course.selection.app.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import com.course.selection.app.service.AdmissionService;
import com.course.selection.app.service.ApplicantService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class AdmissionController {
	
	@Autowired
	AdmissionService admissionService;
	
	// Adding Admission Details to Database
	//Working URL: http://localhost:8080/api/v1/admissions
	@PostMapping("/admissions")
	public ResponseEntity<Admission> saveAdmission(@RequestBody @Valid Admission admission){
		Admission newAdmission = admissionService.saveAdmissionDetails(admission);
		ResponseEntity<Admission> response = new ResponseEntity<Admission>(newAdmission, HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/admissions")
	public ResponseEntity<List<Admission>> getAllAdmissions() {
		List<Admission> admissions = admissionService.getAllAdmissionDetails();
		ResponseEntity<List<Admission>> response = new ResponseEntity<>(admissions, HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/admissions/{admission_id}")
	public ResponseEntity<String> deleteAdmission(@PathVariable("admission_id") @Valid Long admissionId){
		admissionService.deleteAdmissionDetails(admissionId);
		ResponseEntity<String> response = new ResponseEntity<String>("Successfully Deleted", HttpStatus.NO_CONTENT);
		return response;
		
	}
	
	@GetMapping("/admissions/admission/{admission_id}")
	public ResponseEntity<List<Admission>> getAdmissionById(@PathVariable("admission_id") @Valid Long admissionId) {
		Admission admission = admissionService.getAdmissionDetails(admissionId);
		List<Admission> admission_list = new ArrayList();
		admission_list.add(admission);
		ResponseEntity<List<Admission>> response = new ResponseEntity<List<Admission>>(admission_list, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/admissions/{admission_id}")
	public ResponseEntity<Admission> getAdmission(@PathVariable("admission_id") @Valid Long admissionId) {
		Admission admission = admissionService.getAdmissionDetails(admissionId);
		ResponseEntity<Admission> response = new ResponseEntity<Admission>(admission, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/admissions/applicant/{applicant_id}")
	public ResponseEntity<List<Admission>> getAdmissionByApplicantId(@PathVariable("applicant_id") @Valid Long applicantId) {
		List<Admission> admission = admissionService.getAdmissionDetailsByApplicantId(applicantId);
		ResponseEntity<List<Admission>> response = new ResponseEntity<List<Admission>>(admission, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/admissions/courses/{course_id}")
	public ResponseEntity<List<Admission>> showAllAdmissionByCourseId(@PathVariable("course_id") @Valid Long courseId) {
		List<Admission> admission = admissionService.showAllAdmissionDetailsByCourseId(courseId);
		ResponseEntity<List<Admission>> response = new ResponseEntity<List<Admission>>(admission, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/admissions/date/{admission_date}")
	public ResponseEntity<List<Admission>> showAllAdmissionByAdmissionDate(@PathVariable("admission_date") @Valid String admissionDate) {
		List<Admission> admission = admissionService.showAllAdmissionDetailsByDate(admissionDate);
		ResponseEntity<List<Admission>> response = new ResponseEntity<List<Admission>>(admission, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/admissions/admissionstatus/{status}")
	public ResponseEntity<List<Admission>> showAllApplicantsByStatus(@PathVariable("status") @Valid AdmissionStatus status) {
		List<Admission> applicant = admissionService.viewAllApplicantsByStatus(status);
		ResponseEntity<List<Admission>> response = new ResponseEntity<List<Admission>>(applicant, HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/admissions/{admission_id}")
	public ResponseEntity<Admission> updateAdmission(@RequestBody @Valid Admission admission,@PathVariable("admission_id") Long admissionId){
		Admission updateAdmission = admissionService.updateAdmissionDetails( admission,admissionId);
		ResponseEntity<Admission> response = new ResponseEntity<Admission>(updateAdmission, HttpStatus.OK);
		return response;
	}
	
	
	
}
