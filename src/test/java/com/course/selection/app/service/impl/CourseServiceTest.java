package com.course.selection.app.service.impl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.course.selection.app.entity.Course;
import com.course.selection.app.repository.CourseRepository;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
	@InjectMocks
	private CourseServiceImpl courseService;
	
	@Mock
	private CourseRepository courseRepository;
	
	//Get course details
	@Test
	void testGetCourseDetails() {
		
		Long courseId = 200L;
		
		Optional<Course> courseOpt = getCourseDetailsMockData();
		
		Mockito.when(courseRepository.findById(courseId)).thenReturn(courseOpt);
		
		Course course = courseService.getCourseDetails(courseId);
		
		assert(course.getCourseName().equals("Java"));
		assert(course.getCourseFees()==50000);
	}
	
	//Get all course details
	@Test
	void testGetAllCourseDetails() {
		
		List<Course> courses = getAllCoursesMockData();		
		Mockito.when(courseRepository.findAll()).thenReturn(courses);
		
		List<Course> coursesDetails = courseService.getAllCourseDetails();
		
		assert(coursesDetails.size() == courses.size());
		assert(coursesDetails.get(0).getCourseName().equals("Java"));
	}
	
	private Optional<Course> getCourseDetailsMockData(){
		Course course = new Course(200L, "Java", "one year", LocalDate.of(2023,8,01), LocalDate.of(2024,7,30), 50000D);
		return Optional.of(course);
	}
	
	private List<Course> getAllCoursesMockData(){
		List<Course> courses = new ArrayList<>();
		Course course1 = new Course(200L, "Java", "one year", LocalDate.of(2023,8,01), LocalDate.of(2024,7,30), 50000D);
		Course course2 = new Course(201L, "Python", "one year", LocalDate.of(2023,8,01), LocalDate.of(2024,7,30), 40000D);
		Course course3 = new Course(202L, "C++", "one year", LocalDate.of(2023,8,01), LocalDate.of(2024,7,30), 35000D);
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		return courses;
	}
}
