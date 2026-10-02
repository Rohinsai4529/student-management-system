package com.studentmanagement.service;

import com.studentmanagement.dto.LoginResponse;
import com.studentmanagement.dto.StudentResponse;
import com.studentmanagement.entity.Student;

import java.util.List;

public interface StudentService {

    StudentResponse register(Student student);

    LoginResponse login(String email, String password);

    List<StudentResponse> getAllStudents();

    StudentResponse getStudentById(Long id);

    List<StudentResponse> searchStudents(String name);

    StudentResponse updateStudent(Long id, Student student);

    void deleteStudent(Long id);
}