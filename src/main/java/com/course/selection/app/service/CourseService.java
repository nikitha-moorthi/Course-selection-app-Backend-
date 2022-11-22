package com.course.selection.app.service;

import java.util.List;

import com.course.selection.app.entity.Course;

public interface CourseService {
	
	Course saveCourseDetails(Course course);
	void deleteCourseDetails(Long courseId);
	Course updateCourseDetails(Course course,Long courseId);
	Course getCourseDetails(Long courseId);
	List<Course> getAllCourseDetails();

}
