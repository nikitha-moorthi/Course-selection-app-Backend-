package com.course.selection.app.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Applicant")
// Hide Courses at applicant 
@JsonIgnoreProperties(value = {"admission"}, allowGetters = true)
public class Applicant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long applicantId;
	
	@Column(name="Applicant_Name")
	@NotBlank(message="Applicant name is required")
	@Pattern(message = "Invalid applicant name", regexp = "^[a-zA-Z\\s]*$")
	private String applicantName;
	
	@Column(name="Applicant_Mobile_No")
	@NotNull(message="Phone number is required")	
	private Long mobileNumber;
	
	@Column(name="Applicant_Degree")
	@Pattern(message = "Invalid degree name", regexp = "^[a-zA-Z\\s.-]*$")
	@NotNull(message="Degree is required")
	private String applicantDegree;
	
	@Column(name="Applicant_Graduation_Percent")
	@Min(value=0)
	@Max(value=100)
	private Float applicantGraduationPercent;

	@Column(name ="Applicant_Password")
	@NotBlank(message="Password is required")
	private String password;

	public Applicant(Long applicantId) {
		super();
		this.applicantId = applicantId;
	}

	
	
	
}
