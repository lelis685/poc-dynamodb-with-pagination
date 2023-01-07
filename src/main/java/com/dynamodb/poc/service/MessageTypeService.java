package com.dynamodb.poc.service;

import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.dynamodb.poc.controller.data.PaginationRequest;
import com.dynamodb.poc.entity.MessageType;
import com.dynamodb.poc.entity.data.PageableResultSet;
import com.dynamodb.poc.entity.data.Pagination;
import com.dynamodb.poc.repository.MessageTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MessageTypeService {

    private final MessageTypeRepository messageTypeRepository;


    public PageableResultSet<List<MessageType>> getMessagesWithMinUrgencyByDepartment(String department,
                                                                                      PaginationRequest pagination) {

        QueryResultPage<MessageType> messagesResultPage =
                messageTypeRepository.getMessagesWithMinUrgencyByDepartment(department, pagination);

        return buildResults(messagesResultPage, pagination);
    }


    private PageableResultSet<List<MessageType>> buildResults(QueryResultPage<MessageType> messagesResultPage,
                                                              PaginationRequest pagination) {

        Map<String, AttributeValue> lastEvaluatedKeyValues  = messagesResultPage.getLastEvaluatedKey();

        if (lastEvaluatedKeyValues == null) {
            return PageableResultSet.<List<MessageType>>builder()
                    .pagination(Pagination.builder().build())
                    .data(messagesResultPage.getResults())
                    .build();
        }

        List<String> lastEvaluatedKey = lastEvaluatedKeyValues.values()
                .stream()
                .map(AttributeValue::getS)
                .collect(Collectors.toList());
        Collections.reverse(lastEvaluatedKey);
        String next = String.join("*", lastEvaluatedKey);

        Pagination page = Pagination.builder()
                .next(next)
                .pageSize(pagination.getPageSize())
                .build();

        return PageableResultSet.<List<MessageType>>builder()
                .pagination(page)
                .data(messagesResultPage.getResults())
                .build();
    }


}
