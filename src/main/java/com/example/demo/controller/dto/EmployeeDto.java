package com.example.demo.controller.dto;

import lombok.Data;



@Data
public class EmployeeDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String address;
    private int birthYear;
}
