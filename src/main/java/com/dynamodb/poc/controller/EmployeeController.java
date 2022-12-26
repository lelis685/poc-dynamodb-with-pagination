package com.dynamodb.poc.controller;

import com.dynamodb.poc.entity.Employee;
import com.dynamodb.poc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> listEmployee() {
        return employeeRepository.list();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") String employeeId) {
        return employeeRepository.getEmployeeById(employeeId);
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }




}
