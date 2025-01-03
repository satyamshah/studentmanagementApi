package com.test.studentmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.studentmanagementapi.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
