package com.example.demo.controller.dto;

import com.example.demo.model.Interview;
import com.example.demo.model.type.InterviewStepType;
import lombok.Data;

@Data
public class InterviewStepDto {
    private long id;
    private String name;
    private InterviewStepType type;
    private int interview_id;
}
