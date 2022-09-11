package com.example.demo.controller.dto;

import com.example.demo.model.Advertisement;
import com.example.demo.model.Employee;
import lombok.Data;

@Data
public class AdvertisementUserDto {
    private long id;
    private Advertisement advert;
    private Employee employee;
    private String cvPath;
}
