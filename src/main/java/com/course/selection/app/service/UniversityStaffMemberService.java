package com.course.selection.app.service;


import java.util.List;

import com.course.selection.app.entity.Course;
import com.course.selection.app.entity.UniversityStaffMember;

public interface UniversityStaffMemberService {
	
	UniversityStaffMember addStaff(UniversityStaffMember staff);
	UniversityStaffMember updateStaff(UniversityStaffMember staff, Integer staffId);
	UniversityStaffMember viewStaff(Integer staffId);
	void removeStaff(Integer staffId);
	List<UniversityStaffMember> viewAllStaffs();

}
