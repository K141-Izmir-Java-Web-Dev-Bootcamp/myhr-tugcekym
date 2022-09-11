package com.example.demo.controller.dto;

import com.example.demo.model.HumanResource;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class AdvertisementDto {
    private long id;
    private String companyName;
    private String title;
    private String detail;
    private String skills;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    private HumanResource humanResource;

}
