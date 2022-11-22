package com.course.selection.app.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.course.selection.app.constants.AdmissionStatus;
import com.course.selection.app.entity.Admission;
import com.course.selection.app.entity.Applicant;
import com.course.selection.app.entity.Course;

public interface AdmissionService {
	
	Admission saveAdmissionDetails(Admission admission);
	Admission updateAdmissionDetails(Admission admission, Long admissionId);
	List<Admission> getAllAdmissionDetails();
	Admission getAdmissionDetails(Long admissionId);
	void deleteAdmissionDetails(Long admissionId);
	
	List<Admission> showAllAdmissionDetailsByCourseId(Long courseId);
	List<Admission> showAllAdmissionDetailsByDate(String admissionDate);
	
	List<Admission> viewAllApplicantsByStatus(AdmissionStatus status);
	List<Admission> getAdmissionDetailsByApplicantId(Long applicant_id);

}
