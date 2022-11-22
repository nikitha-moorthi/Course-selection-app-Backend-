package com.course.selection.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.selection.app.entity.AdmissionCommiteeMember;


public interface AdmissionCommiteeMemberRepository extends JpaRepository<AdmissionCommiteeMember, Integer> {
	
	List<AdmissionCommiteeMember> findByAdminName(String adminName);
	List<AdmissionCommiteeMember> findByPassword(String password);

}
