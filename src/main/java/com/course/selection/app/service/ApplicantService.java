package com.course.selection.app.service;

import java.util.List;

import com.course.selection.app.constants.AdmissionStatus;
import com.course.selection.app.entity.Applicant;

public interface ApplicantService {
	Applicant saveApplicantDetails(Applicant applicant);
	Applicant updateApplicantDetails(Applicant applicant, Long applicantId);
	List<Applicant> getAllApplicantDetails();
	Applicant getApplicantDetails(Long applicantId);
	void deleteApplicantDetails(Long applicantId);
}
