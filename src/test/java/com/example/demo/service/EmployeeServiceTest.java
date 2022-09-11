package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class EmployeeServiceTest {

    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;
    @Before
    public void setUp() throws Exception {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeRepository);
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

        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);

        Employee createEmployee = employeeService.create(employee);
        Assert.assertEquals(employee, createEmployee);

    }
}