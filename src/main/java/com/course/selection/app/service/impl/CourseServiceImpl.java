package com.course.selection.app.service.impl;

import com.course.selection.app.repository.AdmissionRepository;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.selection.app.entity.Admission;
import com.course.selection.app.entity.Course;
import com.course.selection.app.exception.CourseNotFoundException;
import com.course.selection.app.exception.AdmissionSelectionException;
import com.course.selection.app.repository.CourseRepository;
import com.course.selection.app.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private AdmissionRepository admissionRepository;
	
	@Override
	public Course saveCourseDetails(Course course) {
		if(course.getCourseEndDate().isAfter(course.getCourseStartDate()))
		{
			return courseRepository.save(course);
		}
		
		else
		{
			System.out.println("Course not accepted. DATE");
			throw new CourseNotFoundException("Course Cannot be added, End date cannot precede Start date");
		}
		
	}

	@Override
	public void deleteCourseDetails(Long courseId) {
		Course coursetOpt = courseRepository.findById(courseId).orElse(null);
		if(coursetOpt==null) {
			throw new CourseNotFoundException("CourseId ("+courseId+") Not Found");
		}
		
		else {
			List<Admission> admissions = admissionRepository.findAll();
			List<Admission> filteredAdmission = admissions.stream()
					.filter(admin -> admin.getCourse().getCourseId() == courseId).collect(Collectors.toList());
			if(filteredAdmission.size()==0) {
			courseRepository.deleteById(courseId);	
			}
			else
			{
				throw new CourseNotFoundException("CourseId ("+courseId+") can't be deleted, as it is registered by applicants");
			}
		}
		
	}

	@Override
	public Course getCourseDetails(Long courseId) {
		 Course coursetOpt = courseRepository.findById(courseId).orElse(null);
		 if(coursetOpt==null) {
				throw new CourseNotFoundException("CourseId ("+courseId+") Not Found");
			}else {
		 return coursetOpt;
			}
	}

	@Override
	public List<Course> getAllCourseDetails() {
		return courseRepository.findAll();
	}

	@Override
	public Course updateCourseDetails(Course course,Long courseId) {
		Course updatedCourse = courseRepository.findById(courseId).orElse(null);
		if(updatedCourse!=null) {
			updatedCourse.setCourseName(course.getCourseName());
			updatedCourse.setCourseDuration(course.getCourseDuration());
			
			updatedCourse.setCourseFees(course.getCourseFees());
			if(course.getCourseEndDate().isAfter(course.getCourseStartDate()) )
			{
				updatedCourse.setCourseStartDate(course.getCourseStartDate());
				updatedCourse.setCourseEndDate(course.getCourseEndDate());
			}
			else
			{
				System.out.println("Course not accepted. DATE");
				throw new CourseNotFoundException("Course Cannot be added, End date cannot precede Start date");
			}
			courseRepository.save(updatedCourse);
			return updatedCourse;
		}	
		else
		{
			throw new CourseNotFoundException("CourseId ("+courseId+") Not Found");
		}
		
	}
	
}
