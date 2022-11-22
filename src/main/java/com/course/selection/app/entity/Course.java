package com.course.selection.app.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Course")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long courseId;
	
	@Column(name="Course_Name")
	@Pattern(message = "Invalid course name", regexp = "^[a-zA-Z\\s.-]*$")
	private String courseName;
	
	@Column(name="Course_Duration")
	private String courseDuration;
	
	@Column(name="Course_Start_Date")
	private LocalDate courseStartDate;
	
	@Column(name="Course_End_Date")
	private LocalDate courseEndDate;
	
	@Column(name="Course_Fees")
	@Digits(fraction=0, integer=5)
	private Double courseFees;

	public Course(Long courseId) {
		super();
		this.courseId = courseId;
	}
	
	
	

}
