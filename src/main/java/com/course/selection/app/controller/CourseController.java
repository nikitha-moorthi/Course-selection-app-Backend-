package com.course.selection.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.selection.app.entity.Course;
import com.course.selection.app.service.CourseService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	//Save course details in database
	//http://localhost:8080/api/v1/courses
	@PostMapping("/courses")
	public ResponseEntity<Course> saveCourse(@RequestBody @Valid Course course){
		Course newCourse = courseService.saveCourseDetails(course);
		ResponseEntity<Course> response = new ResponseEntity<Course>(newCourse, HttpStatus.CREATED);
		return response;
	}
	
	//Delete course details from database
	//http://localhost:8080/api/v1/courses/17
	@DeleteMapping("/courses/{course_id}")
	public ResponseEntity<String> deleteCourse(@PathVariable("course_id") @Valid Long courseId){
		courseService.deleteCourseDetails(courseId);
		ResponseEntity<String> response = new ResponseEntity<String>("Successfully Deleted", HttpStatus.NO_CONTENT);
		return response;	
	}
	
	//Update course details and save to database
	//http://localhost:8080/api/v1/courses/18
	@PutMapping("/courses/{course_id}")
	public ResponseEntity<Course> updateCourse(@RequestBody @Valid Course course, @PathVariable("course_id") Long courseId){
		Course updatedCourse = courseService.updateCourseDetails(course, courseId);
		ResponseEntity<Course> reponse = new ResponseEntity<Course>(updatedCourse, HttpStatus.OK);
		return reponse;
	}

	//will not work if Id is not present in DB
	//Get particular course details from database
	//http://localhost:8080/api/v1/courses/18
	@GetMapping("/courses/{course_id}")
	public ResponseEntity<Course> getApplicants(@PathVariable("course_id") @Valid Long courseId) {
		Course course = courseService.getCourseDetails(courseId);
		ResponseEntity<Course> response = new ResponseEntity<Course>(course, HttpStatus.OK);
		return response;
	}

	//Get all course details from database
	//http://localhost:8080/api/v1/courses
	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getAllApplicants() {
		List<Course> courses = courseService.getAllCourseDetails();
		ResponseEntity<List<Course>> response = new ResponseEntity<>(courses, HttpStatus.OK);
		return response;
	}
}
