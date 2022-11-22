package com.course.selection.app.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.course.selection.app.constants.AdmissionStatus;
import com.course.selection.app.entity.Admission;
import com.course.selection.app.entity.Applicant;
import com.course.selection.app.entity.Course;
import com.course.selection.app.exception.AdmissionSelectionException;
import com.course.selection.app.exception.ApplicantNotFoundException;
import com.course.selection.app.exception.CourseNotFoundException;
import com.course.selection.app.repository.AdmissionRepository;
import com.course.selection.app.repository.ApplicantRepository;
import com.course.selection.app.repository.CourseRepository;
import com.course.selection.app.service.AdmissionService;

@Service
public class AdmissionServiceImpl implements AdmissionService {
	@Autowired
	private AdmissionRepository admissionRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private ApplicantRepository applicantRepository;

	@Override
	public Admission saveAdmissionDetails(Admission admission) {

		if (admission.getAdmissionDate().isAfter(LocalDate.now())
				|| admission.getAdmissionDate().equals(LocalDate.now())) {
			admission.setStatus(AdmissionStatus.APPLIED);
			return admissionRepository.save(admission);
		} else
			throw new AdmissionSelectionException("Enter Correct date.");
	}


	@Override
	public Admission updateAdmissionDetails(Admission admission, Long admissionId) {
		Admission admissionUpdated = admissionRepository.findById(admissionId).orElse(null);

		if (!(admissionUpdated == null)) {

			if (admission.getAdmissionDate().isAfter(LocalDate.now())
					|| admission.getAdmissionDate().equals(LocalDate.now())) {
				admissionUpdated.setAdmissionDate(admission.getAdmissionDate());
				return admissionRepository.save(admissionUpdated);

			} else
				throw new AdmissionSelectionException("Enter Correct date.");
		} else {
			throw new AdmissionSelectionException("admissionId (" + admissionId + ") Not Found");

		}
	}

	@Override
	public List<Admission> getAllAdmissionDetails() {

		return admissionRepository.findAll();
	}

	@Override
	public Admission getAdmissionDetails(Long admissionId) {

		Admission admission = admissionRepository.findById(admissionId).orElse(null);
		if (!(admission == null)) {
			return admission;
		} else
			throw new AdmissionSelectionException("admissionId (" + admissionId + ") Not Found");
	}

	@Override
	public void deleteAdmissionDetails(Long admissionId) {
		Admission admission = admissionRepository.findById(admissionId).orElse(null);

		if (!(admission == null)) {
			admissionRepository.deleteById(admissionId);
		} else
			throw new AdmissionSelectionException("admissionId (" + admissionId + ") Not Found");

	}

	@Override
	public List<Admission> showAllAdmissionDetailsByCourseId(Long courseId) {

		Course coursetOpt = courseRepository.findById(courseId).orElse(null);
		if (coursetOpt == null) {
			throw new CourseNotFoundException("CourseId (" + courseId + ") Not Found");
		} else {
			List<Admission> admissions = admissionRepository.findAll();
			List<Admission> filteredAdmission = admissions.stream()
					.filter(admin -> admin.getCourse().getCourseId() == courseId).collect(Collectors.toList());
			return filteredAdmission;
		}
	}

	@Override
	public List<Admission> showAllAdmissionDetailsByDate(String admissionDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		// convert String to LocalDate
		LocalDate localDate = LocalDate.parse(admissionDate, formatter);

		List<Admission> admissions = admissionRepository.findByAdmissionDate(localDate);
		if (admissions.size() == 0) {
			throw new AdmissionSelectionException("admissionDate (" + admissionDate + ") Not Found");
		} else
			return admissions;
		/*
		 * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 * //convert String to LocalDate LocalDate localDate =
		 * LocalDate.parse(admissionDate, formatter); admissions.stream().filter(admin
		 * -> (admin.getAdmissionDate()).compareTo(localDate) == 0
		 * ).collect(Collectors.toList());
		 */

	}

	@Override
	public List<Admission> viewAllApplicantsByStatus(AdmissionStatus status) {
		List<Admission> applicants = admissionRepository.findAll();
		List<Admission> filteredAdmission = applicants.stream()
				.filter(admin -> (admin.getStatus()).compareTo(status) == 0).collect(Collectors.toList());
		return filteredAdmission;

	}
	@Override
	public List<Admission> getAdmissionDetailsByApplicantId(Long applicant_id) {
		// TODO Auto-generated method stub
		List<Admission> applicants = admissionRepository.findAll();
		List<Admission> filteredAdmission = applicants.stream()
				.filter(admin -> (admin.getApplicant().getApplicantId() == applicant_id)).collect(Collectors.toList());
		return filteredAdmission;
	}

}
