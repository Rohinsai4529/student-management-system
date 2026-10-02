package com.studentmanagement.controller;

import com.studentmanagement.dto.LoginRequest;
import com.studentmanagement.dto.LoginResponse;
import com.studentmanagement.dto.StudentResponse;
import com.studentmanagement.entity.Student;
import com.studentmanagement.service.StudentService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public StudentResponse register(@RequestBody Student student) {
        return studentService.register(student);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return studentService.login(
                request.getEmail(),
                request.getPassword()
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public List<StudentResponse> searchStudents(
            @RequestParam String name) {

        return studentService.searchStudents(name);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public StudentResponse getStudentById(
            @PathVariable Long id) {

        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public StudentResponse updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return "Student deleted successfully";
    }
}