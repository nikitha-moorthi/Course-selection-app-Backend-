package com.course.selection.app.service.impl;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.course.selection.app.entity.Applicant;
import com.course.selection.app.repository.ApplicantRepository;

@ExtendWith(MockitoExtension.class)
public class ApplicantServiceTest {
	
	@InjectMocks
	private ApplicantServiceImpl applicantService;
	
	@Mock
	private ApplicantRepository applicantRepository;
	
	//Save applicant details
	@Test
	void testSaveApplicantDetails() {
		
		Optional<Applicant> applicantOpt = getApplicantMockData();
		Applicant applicant = applicantOpt.get();
		Mockito.when(applicantRepository.save(applicant)).thenReturn(applicant);
		
		Applicant app = applicantService.saveApplicantDetails(applicant);
		
		assert(app.getApplicantName().equals(applicant.getApplicantName()));
		assert(app.getMobileNumber().equals(applicant.getMobileNumber()));

	}
	
	//Update applicant details
	@Test
	void testUpdateApplicantDetails() {
		
		Long applicantId = 101L;
		Optional<Applicant> applicantOpt = getApplicantMockData();
		Applicant app = applicantOpt.get();
		Mockito.when(applicantRepository.findById(applicantId)).thenReturn(applicantOpt);
		Mockito.when(applicantRepository.save(app)).thenReturn(app);
		
		Applicant updateApplicant = applicantService.updateApplicantDetails(app, applicantId);
		
		assert(app.getApplicantName().equals(updateApplicant.getApplicantName()));
		assert(app.getMobileNumber().equals(updateApplicant.getMobileNumber()));
		
	}
	
	private Optional<Applicant> getApplicantMockData(){
		Applicant applicant = new Applicant(100L, "Tharun", 8978676767L, "EEE", 99.99F,"password");
		return Optional.of(applicant);
	}

}

