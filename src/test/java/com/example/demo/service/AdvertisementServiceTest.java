package com.example.demo.service;

import com.example.demo.model.Advertisement;
import com.example.demo.model.AdvertisementUser;
import com.example.demo.model.Employee;
import com.example.demo.model.HumanResource;
import com.example.demo.repository.AdvertisementRepository;
import com.example.demo.repository.AdvertisementUserRepository;
import com.example.demo.repository.HumanResourceRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

public class AdvertisementServiceTest {

    private AdvertisementService advertisementService;

    private AdvertisementRepository advertisementRepository;
    private AdvertisementUserRepository advertisementUserRepository;

    @org.junit.Before
    public void setUp() throws Exception {
        advertisementRepository = Mockito.mock(AdvertisementRepository.class);
        advertisementUserRepository = Mockito.mock(AdvertisementUserRepository.class);

        advertisementService = new AdvertisementService(advertisementRepository,advertisementUserRepository);
    }

    @Test
    public void whenCreateAdvertisementCalledWithValidReq_shouldReturnAdvertisement() {

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

        Mockito.when(advertisementRepository.save(advertisement)).thenReturn(advertisement);

        Advertisement createAdvertisement = advertisementService.create(advertisement);

        Assert.assertEquals(createAdvertisement, advertisement);

        Mockito.verify(advertisementRepository).save(advertisement);
    }

    @Test
    public void whenAddAdvertisementUserCalledWithValidReq_shouldReturnAdvertisementUser() {
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

        Employee employee = Employee.builder()
                .id(1)
                .username("veli123")
                .password("veli123455")
                .email("veli@gm.com")
                .address("Çankaya / Ankara")
                .fullName("Veli Küçük")
                .birthYear(1987)
                .build();


        AdvertisementUser advertisementUser = AdvertisementUser.builder()
                .id(1)
                .employee(employee)
                .advert(advertisement)
                .build();

        Mockito.when(advertisementUserRepository.save(advertisementUser)).thenReturn(advertisementUser);

        AdvertisementUser createdAdvertisementUser = advertisementService.addUsers(advertisementUser);
        Assert.assertEquals(advertisementUser, createdAdvertisementUser);
    }
}