package com.dynamodb.poc.controller;

import com.dynamodb.poc.entity.Employee;
import com.dynamodb.poc.repository.PagingEmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/employees")
public class EmployeeController {


    private PagingEmployeeRepository pagingEmployeeRepository;

    public EmployeeController(PagingEmployeeRepository pagingEmployeeRepository) {
        this.pagingEmployeeRepository = pagingEmployeeRepository;
    }

    @GetMapping
    public Page<Employee> listEmployee(@PageableDefault(size = 1) Pageable pageable) {
        return pagingEmployeeRepository.findAll(pageable);
    }


}
