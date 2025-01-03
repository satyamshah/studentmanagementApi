package com.test.studentmanagementapi.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.studentmanagementapi.model.Student;
import com.test.studentmanagementapi.service.StudentService;
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController {

    private static final Logger logger = LogManager.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @GetMapping("/getStudent")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            logger.info("Fetching all students...");
            List<Student> students = studentService.getAllStudents();
            logger.info("Successfully fetched {} students", students.size());
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            logger.error("Error occurred while fetching students: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/addstudents")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        try {
            logger.info("Adding a new student: {}", student);
            Student savedStudent = studentService.saveStudent(student);
            logger.info("Successfully added student with ID: {}", savedStudent.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
        } catch (Exception e) {
            logger.error("Error occurred while adding student: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/editstudents")
    public ResponseEntity<Student> editStudent(@RequestBody Student studentDetails) {
        try {
            logger.info("Editing student with ID: {}", studentDetails.getId());
            Optional<Student> optionalStudent = Optional.ofNullable(studentService.getStudentById(studentDetails.getId()));

            if (optionalStudent.isPresent()) {
                Student existingStudent = optionalStudent.get();
                existingStudent.setName(studentDetails.getName());
                existingStudent.setAge(studentDetails.getAge());
                existingStudent.setClassname(studentDetails.getClassname());
                existingStudent.setPhonenumber(studentDetails.getPhonenumber());

                Student updatedStudent = studentService.saveStudent(existingStudent);
                logger.info("Successfully updated student with ID: {}", updatedStudent.getId());
                return ResponseEntity.ok(updatedStudent);
            } else {
                logger.warn("Student with ID {} not found", studentDetails.getId());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            logger.error("Error occurred while editing student: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        try {
            logger.info("Deleting student with ID: {}", id);
            boolean isDeleted = studentService.deleteStudent(id);

            if (isDeleted) {
                logger.info("Successfully deleted student with ID: {}", id);
                return ResponseEntity.ok("Student deleted successfully");
            } else {
                logger.warn("Student with ID {} not found", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
            }
        } catch (Exception e) {
            logger.error("Error occurred while deleting student: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting student");
        }
    }
}
