package com.dynamodb.poc.controller;

import com.dynamodb.poc.entity.SupportMessage;
import com.dynamodb.poc.repository.SuportMessageRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/suport-messages")
public class SupporMessageController {

    private final SuportMessageRepository suportMessageRepository;


    @GetMapping
    public List<SupportMessage> getAllForDepartment(@RequestParam String department) {
        return suportMessageRepository.getAllForDepartment(department);
    }


    @GetMapping(params = "day")
    public List<SupportMessage> getAllForDepartmentOnDay(@RequestParam String department, @RequestParam String day) {
        return suportMessageRepository.getAllForDepartmentOnDay(department, day);
    }


}
