package com.studentmanagement.dto;

public class StudentResponse {

    private Long id;
    private String name;
    private String mobile;
    private String email;
    private String role;

    public StudentResponse() {
    }

    public StudentResponse(Long id, String name, String mobile,
                           String email, String role) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}