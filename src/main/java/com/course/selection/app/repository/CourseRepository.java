package com.course.selection.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.selection.app.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
