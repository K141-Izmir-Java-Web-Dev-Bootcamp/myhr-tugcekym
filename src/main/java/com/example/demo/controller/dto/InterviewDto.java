package com.example.demo.controller.dto;

import com.example.demo.model.Advertisement;
import com.example.demo.model.Employee;
import com.example.demo.model.InterviewStep;
import lombok.Data;

import java.util.List;

@Data
public class InterviewDto {
    private Long id;
    private Advertisement advertisement;
    private List<InterviewStep> steps;
    private Employee employee;
}



