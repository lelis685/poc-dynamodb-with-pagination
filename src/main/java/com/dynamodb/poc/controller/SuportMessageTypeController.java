package com.dynamodb.poc.controller;

import com.dynamodb.poc.controller.data.PaginationRequest;
import com.dynamodb.poc.entity.MessageType;
import com.dynamodb.poc.entity.SupportMessage;
import com.dynamodb.poc.entity.data.PageableResultSet;
import com.dynamodb.poc.repository.MessageTypeRepository;
import com.dynamodb.poc.repository.SuportMessageRepository;
import com.dynamodb.poc.service.MessageTypeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/messages-type")
public class SuportMessageTypeController {

    private final MessageTypeService messageTypeService;


    @GetMapping
    public PageableResultSet<List<MessageType>> getAllForDepartment(@RequestParam String department,
                                                                    PaginationRequest paginationRequest) {

        return messageTypeService.getMessagesWithMinUrgencyByDepartment(department, paginationRequest);

    }




}