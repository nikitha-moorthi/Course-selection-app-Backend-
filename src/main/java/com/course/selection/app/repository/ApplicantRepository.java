package com.course.selection.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.selection.app.entity.Applicant;


public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
	List<Applicant> findByApplicantName(String applicantName);
	List<Applicant> findByPassword(String password);
}
