package com.example.demo.service;

import com.example.demo.error.NotFountException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }
    public Employee update(long id, Employee postEmployee) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFountException("Employee " + id + " does not exist"));

        employee.setEmail(postEmployee.getEmail());
        employee.setUsername(postEmployee.getUsername());
        employee.setFullName(postEmployee.getFullName());
        employee.setPassword(postEmployee.getPassword());
        employee.setAddress(postEmployee.getAddress());
        employee.setBirthYear(postEmployee.getBirthYear());
        return employeeRepository.save(employee);
    }
    public void delete(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFountException("Employee " + id + " does not exist"));
        employeeRepository.delete(employee);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
    public Employee getById(Long id) {
        Optional<Employee> result = employeeRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new NotFountException("Employee " + id + " does not exist");
        }
    }
}
