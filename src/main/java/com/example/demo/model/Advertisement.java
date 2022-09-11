package com.example.demo.model;

import com.example.demo.controller.HumanResourceController;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode

public class Advertisement {

    @Id
    @GeneratedValue
    private long id;

    private String companyName;

    private String title;

    private String detail;

    private String skills;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @OneToOne
    @JoinColumn(name = "human_id", referencedColumnName = "id")
    private HumanResource humanResource;

}
