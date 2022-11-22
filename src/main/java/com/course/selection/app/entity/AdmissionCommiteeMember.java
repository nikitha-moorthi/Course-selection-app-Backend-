package com.course.selection.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="AdmissionCommiteeMember")
public class AdmissionCommiteeMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer adminId;
	
	@Column(name="Admin_Name")
	@NotBlank(message="Admin name is required")
	@Pattern(message = "Invalid Admin name", regexp = "^[a-zA-Z\\s.-]*$")
	private String adminName;
	
	@Column(name="Admin_Contact")
	@NotNull(message="Phone number is required")
	private Long adminContact;
	
	@Column(name ="Admin_Password")
	@NotBlank(message="Password is required")
	private String password;

	
}
