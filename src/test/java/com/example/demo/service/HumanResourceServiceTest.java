package com.example.demo.service;

import com.example.demo.model.HumanResource;
import com.example.demo.repository.HumanResourceRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class HumanResourceServiceTest {

    private HumanResourceService humanResourceService;
    private HumanResourceRepository humanResourceRepository;
    @Before
    public void setUp() throws Exception {
        humanResourceRepository = Mockito.mock(HumanResourceRepository.class);
        humanResourceService = new HumanResourceService(humanResourceRepository);
    }
    @Test
    public void whenCreateHumanResourceCalledWithValidReq_shouldReturnHumanResource() {
        HumanResource humanResource = HumanResource.builder()
                .id(1)
                .username("selinay34")
                .password("selinay345566")
                .fullName("Selinay Kantarcı")
                .email("selinay@gm.com")
                .build();

        Mockito.when(humanResourceRepository.save(humanResource)).thenReturn(humanResource);
        HumanResource createdHumanResource = humanResourceService.create(humanResource);
        Assert.assertEquals(humanResource,createdHumanResource);
    }

}