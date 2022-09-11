package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.model.type.InterviewStepType;
import com.example.demo.repository.InterviewRepository;
import com.example.demo.repository.InterviewStepRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class InterviewServiceTest {

    private InterviewService interviewService;
    private InterviewRepository interviewRepository;

    private InterviewStepRepository interviewStepRepository;

    @Before
    public void setUp() throws Exception {
    interviewRepository = Mockito.mock(InterviewRepository.class);
    interviewStepRepository = Mockito.mock(InterviewStepRepository.class);
    interviewService = new InterviewService(interviewRepository, interviewStepRepository);
    }

    @Test
    public void whenCreateEmployeeCalledWithValidReq_shouldReturnEmployee() {

        Employee employee = Employee.builder()
                .id(1)
                .username("veli123")
                .password("veli123455")
                .email("veli@gm.com")
                .address("Çankaya / Ankara")
                .fullName("Veli Küçük")
                .birthYear(1987)
                .build();

        HumanResource humanResource = HumanResource.builder()
                .id(1)
                .username("ahmet356")
                .password("ahmet3450969594")
                .fullName("Ahmet Karahan")
                .email("ahmetkarahan@proto.mail").build();


        Advertisement advertisement = Advertisement.builder()
                .companyName("Facebook")
                .title("Backend Developer")
                .detail("At least 2 years of experience.")
                .skills("Java - Go - NoSQL - OOP")
                .startDate(new Date())
                .endDate(new Date())
                .humanResource(humanResource).build();

        Interview interview = Interview.builder()
                .id(1)
                .employee(employee)
                .advertisement(advertisement)
                .build();

        Mockito.when(interviewRepository.save(interview)).thenReturn(interview);

        Interview createdInterview = interviewService.create(interview);
        Assert.assertEquals(createdInterview, interview);
    }
}