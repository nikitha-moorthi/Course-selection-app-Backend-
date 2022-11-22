package com.course.selection.app.service;
import java.util.List;

import com.course.selection.app.entity.Admission;
import com.course.selection.app.entity.AdmissionCommiteeMember;
import com.course.selection.app.entity.Applicant;


public interface AdmissionCommiteeMemberService  {
	
	AdmissionCommiteeMember addCommiteeMember(AdmissionCommiteeMember admissionCommiteeMember);
	AdmissionCommiteeMember updateCommiteeMember(AdmissionCommiteeMember admissionCommiteeMember, Integer adminId);
	AdmissionCommiteeMember viewCommiteeMember(Integer adminId);
	void removeCommiteeMember(Integer adminId);
	List<AdmissionCommiteeMember> viewAllCommiteeMembers();
	void provideAdmissionResult(Long applicantId, Admission admission);
	

}
