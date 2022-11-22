package com.course.selection.app.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CourseSelectionExceptionHandler {	
		
		@ResponseStatus(code = HttpStatus.BAD_REQUEST)
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public LinkedHashMap<String, String> handleBardRequest(MethodArgumentNotValidException me){
			
			LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>();		
			
			me.getFieldErrors().stream().forEach(fieldError -> {
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
			});
					
			return errors;
		}

		@ResponseStatus(code = HttpStatus.NOT_FOUND)
		@ExceptionHandler(ApplicantNotFoundException.class)
		public LinkedHashMap<String, String> handleApplicantNotFound(ApplicantNotFoundException e) {
			LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>();	
			errors.put("ApplicantId",e.getMessage());
			return errors;
		}
		
		@ResponseStatus(code = HttpStatus.NOT_FOUND)
		@ExceptionHandler(CourseNotFoundException.class)
		public LinkedHashMap<String, String> handleCourseNotFound(CourseNotFoundException e) {
			LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>();	
			errors.put("CourseId",e.getMessage());
			return errors;
		}
		
		@ResponseStatus(code = HttpStatus.NOT_FOUND)
		@ExceptionHandler(AdmissionSelectionException.class)
		public LinkedHashMap<String, String> handleCourseSelection(AdmissionSelectionException e) {
			LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>();	
			errors.put("AdmissionId" ,e.getMessage());
			return errors;
		}
		
		@ResponseStatus(code = HttpStatus.NOT_FOUND)
		@ExceptionHandler(AdminNotFoundException.class)
		public LinkedHashMap<String, String> handleAdminNotFound(AdminNotFoundException e) {
			LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>();	
			errors.put("AdminId",e.getMessage());
			return errors;
		}
		
		@ResponseStatus(code = HttpStatus.NOT_FOUND)
		@ExceptionHandler(StaffNotFoundException.class)
		public LinkedHashMap<String, String> handleStaffNotFound(StaffNotFoundException e) {
			LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>();	
			errors.put("StaffId",e.getMessage());
			return errors;
		}
		
		
	}


