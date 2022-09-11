package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode

public class Interview {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "advert_id", referencedColumnName = "id")
    private Advertisement advertisement;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @OneToMany
    @JoinColumn(name = "interview_id", referencedColumnName = "id")
    private List<InterviewStep> steps;
}
