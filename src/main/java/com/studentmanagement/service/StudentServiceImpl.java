package com.studentmanagement.service;

import com.studentmanagement.dto.LoginResponse;
import com.studentmanagement.dto.StudentResponse;
import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public StudentServiceImpl(
            StudentRepository studentRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    private StudentResponse toResponse(Student student) {

        if (student == null) {
            return null;
        }

        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getMobile(),
                student.getEmail(),
                student.getRole()
        );
    }

    @Override
    public StudentResponse register(Student student) {

        student.setRole("STUDENT");

        student.setPassword(
                passwordEncoder.encode(student.getPassword())
        );

        Student savedStudent =
                studentRepository.save(student);

        return toResponse(savedStudent);
    }

    @Override
    public LoginResponse login(
            String email,
            String password) {

        Student student =
                studentRepository.findByEmail(email);

        if (student != null &&
            passwordEncoder.matches(
                password,
                student.getPassword())) {

            String token =
                    jwtService.generateToken(
                            student.getEmail(),
                            student.getRole()
                    );

            StudentResponse response =
                    toResponse(student);

            return new LoginResponse(
                    token,
                    response
            );
        }

        return null;
    }

    @Override
    public List<StudentResponse> getAllStudents() {

        return studentRepository
                .findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public StudentResponse getStudentById(Long id) {

        Student student =
                studentRepository
                        .findById(id)
                        .orElse(null);

        return toResponse(student);
    }

    @Override
    public List<StudentResponse> searchStudents(
            String name) {

        return studentRepository
                .findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public StudentResponse updateStudent(
            Long id,
            Student student) {

        Student existing =
                studentRepository
                        .findById(id)
                        .orElse(null);

        if (existing != null) {

            existing.setName(student.getName());
            existing.setMobile(student.getMobile());
            existing.setEmail(student.getEmail());
            existing.setRole(student.getRole());

            if (student.getPassword() != null &&
                !student.getPassword().isBlank()) {

                existing.setPassword(
                        passwordEncoder.encode(
                                student.getPassword()
                        )
                );
            }

            Student updatedStudent =
                    studentRepository.save(existing);

            return toResponse(updatedStudent);
        }

        return null;
    }

    @Override
    public void deleteStudent(Long id) {

        studentRepository.deleteById(id);
    }
}