package com.course.selection.app.service.impl;

import static org.mockito.Mockito.doNothing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.course.selection.app.constants.AdmissionStatus;
import com.course.selection.app.entity.Admission;
import com.course.selection.app.entity.Applicant;
import com.course.selection.app.entity.Course;
import com.course.selection.app.repository.AdmissionRepository;


@ExtendWith(MockitoExtension.class)
public class AdmissionServiceTest {
	
	@InjectMocks
	private AdmissionServiceImpl admissionService;
	
	@Mock
	private AdmissionRepository admissionRepository;
	
	//delete admission details
	@Test	
	void testDeleteAdmissionDetails() {
		Long admissionId = 100L;
		Optional<Admission> admOpt = getAdmissionMockData();
		Admission admMock = admOpt.get();
		//Mockito.when(admissionRepository.findAll()).thenReturn((List<Admission>) admMock);
		doNothing().when(admissionRepository).deleteById(admissionId);
	
		admissionService.deleteAdmissionDetails(admissionId);
		
		assert(admMock.getAdmissionId().equals(100L));
	}
	
	//Get admission details
		@Test
		void testGetAdmissionDetails() {
			
			Long admissionId = 100L;
			
			Optional<Admission> admOpt = getAdmissionMockData();
			
			Mockito.when(admissionRepository.findById(admissionId)).thenReturn(admOpt);
			
			Admission admission = admissionService.getAdmissionDetails(admissionId);
			
			assert(admission.getAdmissionId()==100L);
			assert(admission.getStatus().equals(AdmissionStatus.APPLIED));
		}		
	
	
	private Optional<Admission> getAdmissionMockData(){
		Course course = new Course(200L, "Java", "one year", LocalDate.of(2023,8,01), LocalDate.of(2024,7,30), 50000D );
		Applicant applicant = new Applicant(100L, "Tharun", 8978676767L, "EEE", 99.99F,"password");
		Admission adm = new Admission(100L, course, applicant, LocalDate.of(2023,8,01), AdmissionStatus.APPLIED);
		return Optional.of(adm);
	}
	

}
