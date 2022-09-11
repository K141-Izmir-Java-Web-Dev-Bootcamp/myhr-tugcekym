package com.example.demo.controller.dto;

import lombok.Data;

@Data
public class HumanResourceDto {
    private long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
}
