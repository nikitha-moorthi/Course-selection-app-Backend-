package com.course.selection.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.selection.app.entity.Admission;
import com.course.selection.app.entity.Applicant;

public interface AdmissionRepository extends JpaRepository<Admission, Long>{

	List<Admission> findByApplicant(Applicant applicant);

	List<Admission> findByAdmissionDate(LocalDate admissionDate);
	

}
