package com.course.selection.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="University_Staff")
@JsonIgnoreProperties(value="courses",allowGetters = true)
public class UniversityStaffMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Staff_Id")
	private Integer staffId;
	
	
	@Column(name="Role")
	private String role;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Staff_Contact")
	private Long staffContact;

	public Integer getStaffId() {
		return staffId;
	}
	@OneToMany(cascade = CascadeType.ALL)	
	List<Course> courses;
	
	
	public UniversityStaffMember(Integer staffId, String password, String role) {
		
		this.staffId = staffId;
		this.password = password;
		this.role = role;
	}
	

}
