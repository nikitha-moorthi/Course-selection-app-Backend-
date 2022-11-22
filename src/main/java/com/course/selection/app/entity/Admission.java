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
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.course.selection.app.constants.AdmissionStatus;
import com.course.selection.app.entity.Course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Admission")
public class Admission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long admissionId;
	
	@NotNull
	@OneToOne
	@JoinColumn(name ="courseId")
	private Course course;
	
	@NotNull
	@OneToOne
	@JoinColumn(name ="applicantId")
	private Applicant applicant;
	

	@Column(name ="Admission_Date")
	private LocalDate admissionDate;
	
	@Column(name ="status")
	private AdmissionStatus status;

	
}
