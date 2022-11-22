package com.course.selection.app.service.impl;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.course.selection.app.entity.AdmissionCommiteeMember;
import com.course.selection.app.repository.AdmissionCommiteeMemberRepository;


@ExtendWith(MockitoExtension.class)
public class AdmissionCommiteeMemberServiceTest {
	

	@InjectMocks
	private AdmissionCommiteeMemberServiceImpl adminService;
	
	@Mock
	private AdmissionCommiteeMemberRepository adminRepository;
	
	//Save admin details
	@Test
	void testSaveAdminDetails() {
		
		Optional<AdmissionCommiteeMember> adminOpt = getAdminMockData();
		AdmissionCommiteeMember admin = adminOpt.get();
		Mockito.when(adminRepository.save(admin)).thenReturn(admin);
		
		AdmissionCommiteeMember adminMember = adminService.addCommiteeMember(admin);
		
		assert(adminMember.getAdminName().equals(admin.getAdminName()));
		assert(adminMember.getAdminContact().equals(admin.getAdminContact()));

	}
	
	//Get admin details
		@Test
		void testViewCommiteeMember() {
			
			Integer adminId = 200;
			
			Optional<AdmissionCommiteeMember> adminOpt = getAdminMockData();
			
			Mockito.when(adminRepository.findById(adminId)).thenReturn(adminOpt);
			
			AdmissionCommiteeMember admin = adminService.viewCommiteeMember(adminId);
			
			assert(admin.getAdminName().equals("Raju"));
			assert(admin.getAdminContact()==8667245460L);
		}
		
		
	
	private Optional<AdmissionCommiteeMember> getAdminMockData(){
		AdmissionCommiteeMember admin = new AdmissionCommiteeMember(200, "Raju", 8667245460L,"password");
		return Optional.of(admin);
	}
	

}
