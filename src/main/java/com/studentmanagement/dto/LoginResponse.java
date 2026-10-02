package com.studentmanagement.dto;

public class LoginResponse {

    private String token;
    private StudentResponse student;

    public LoginResponse() {
    }

    public LoginResponse(String token, StudentResponse student) {
        this.token = token;
        this.student = student;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public StudentResponse getStudent() {
        return student;
    }

    public void setStudent(StudentResponse student) {
        this.student = student;
    }
}