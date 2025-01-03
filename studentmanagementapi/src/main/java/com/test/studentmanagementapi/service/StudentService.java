package com.test.studentmanagementapi.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.studentmanagementapi.model.Student;
import com.test.studentmanagementapi.repository.StudentRepository;

@Service
public class StudentService {

    private static final Logger logger = LogManager.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        try {
            logger.info("Saving student: {}", student);
            Student savedStudent = studentRepository.save(student);
            logger.info("Successfully saved student with ID: {}", savedStudent.getId());
            return savedStudent;
        } catch (Exception e) {
            logger.error("Error occurred while saving student: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<Student> getAllStudents() {
        try {
            logger.info("Fetching all students...");
            List<Student> students = studentRepository.findAll();
            logger.info("Successfully fetched {} students", students.size());
            return students;
        } catch (Exception e) {
            logger.error("Error occurred while fetching students: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Student getStudentById(Long id) {
        try {
            logger.info("Fetching student with ID: {}", id);
            return studentRepository.findById(id)
                    .orElseThrow(() -> {
                        logger.warn("Student with ID {} not found", id);
                        return new RuntimeException("Student not found with ID: " + id);
                    });
        } catch (Exception e) {
            logger.error("Error occurred while fetching student with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public boolean deleteStudent(Long id) {
        try {
            logger.info("Deleting student with ID: {}", id);
            if (studentRepository.existsById(id)) {
                studentRepository.deleteById(id);
                logger.info("Successfully deleted student with ID: {}", id);
                return true;
            } else {
                logger.warn("Student with ID {} not found", id);
                return false;
            }
        } catch (Exception e) {
            logger.error("Error occurred while deleting student with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }
}
