package com.course.selection.app.service.impl;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.course.selection.app.entity.UniversityStaffMember;
import com.course.selection.app.repository.UniversityStaffMemberRepository;

@ExtendWith(MockitoExtension.class)
public class UniversityStaffMemberServiceTest {

	@InjectMocks
	private UniversityStaffMemberServiceImpl universityStaffMemberService;
	
	@Mock
	private UniversityStaffMemberRepository universityStaffMemberRepository;
	
	@Test
	void testDeleteUniversityStaffMember() {
		
		Integer staffId = 174;
		Optional<UniversityStaffMember> staffOpt = getStaffDetailsMockData();
		UniversityStaffMember staff = staffOpt.get();
		doNothing().when(universityStaffMemberRepository).deleteById(staffId);
	
		universityStaffMemberService.removeStaff(staffId);
		
		assert(staff.getRole().equals("staff"));
		
	}
	
	//Get universityStaffMember details
	@Test
	void testGetCourseDetails() {
		
		Integer staffId = 174;
		String password="password";
		
		Optional<UniversityStaffMember> staffOpt = getStaffDetailsMockData();
		
		Mockito.when(universityStaffMemberRepository.findById(staffId)).thenReturn(staffOpt);
		
		UniversityStaffMember staff = universityStaffMemberService.viewStaff(staffId);
		
		assert(staff.getRole().equals("staff"));
		assert(staff.getStaffId().equals(174));
	}
	
	//Get all universityStaffMember details
	@Test
	void testGetAllCourseDetails() {
		
		List<UniversityStaffMember> staffs = getAllCoursesMockData();		
		Mockito.when(universityStaffMemberRepository.findAll()).thenReturn(staffs);
		
		List<UniversityStaffMember> staffsDetails = universityStaffMemberService.viewAllStaffs();
		
		assert(staffsDetails.size() == staffs.size());
		assert(staffsDetails.get(0).getPassword().equals("password175"));
	}
	

	private List<UniversityStaffMember> getAllCoursesMockData() {
		List<UniversityStaffMember> courses = new ArrayList<>();
		UniversityStaffMember staff1 = new UniversityStaffMember(175, "password175", "staff1");
		UniversityStaffMember staff2 = new UniversityStaffMember(176, "password176", "staff2");
		UniversityStaffMember staff3 = new UniversityStaffMember(177, "password177", "staff3");
		courses.add(staff1);
		courses.add(staff2);
		courses.add(staff3);
		return courses;
	}

	private Optional<UniversityStaffMember> getStaffDetailsMockData() {
		UniversityStaffMember staff = new UniversityStaffMember(174, "password", "staff");
		return Optional.of(staff);
	}	
}
