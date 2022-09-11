package com.example.demo.model;

import com.example.demo.model.type.InterviewStepType;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode

public class InterviewStep {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private InterviewStepType type;

    private int interview_id;
}
