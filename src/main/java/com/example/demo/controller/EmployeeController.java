package com.example.demo.controller;

import com.example.demo.controller.dto.EmployeeDto;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private ModelMapper modelMapper;
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping("employee")
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto dto) {
        Employee EmployeeRequest = modelMapper.map(dto, Employee.class);
        Employee employee = employeeService.create(EmployeeRequest);
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);
        return new ResponseEntity<EmployeeDto>(employeeResponse, HttpStatus.CREATED);
    }

    @GetMapping("employees")
    public List<EmployeeDto> gets() {
        return employeeService.getAll().stream().map(post -> modelMapper.map(post, EmployeeDto.class))
                .collect(Collectors.toList());
    }
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> get(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);
        return ResponseEntity.ok().body(employeeResponse);
    }
    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
        Employee postRequest = modelMapper.map(employeeDto, Employee.class);
        Employee employee = employeeService.update(id, postRequest);
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);
        return ResponseEntity.ok().body(employeeResponse);
    }
    @DeleteMapping("/employee/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        employeeService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


}
