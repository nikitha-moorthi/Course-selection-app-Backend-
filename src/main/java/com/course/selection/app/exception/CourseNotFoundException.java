package com.course.selection.app.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseNotFoundException extends RuntimeException{

	 private String message;
}
