package com.dynamodb.poc.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.dynamodb.poc.controller.data.PaginationRequest;
import com.dynamodb.poc.entity.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class MessageTypeRepository {
    private static final String MIN_URGENCY = "0#";


    private final DynamoDBMapper mapper;


    public QueryResultPage<MessageType> getMessagesWithMinUrgencyByDepartment(String department, PaginationRequest pagination) {

        MessageType messageType = new MessageType();
        messageType.setDepartment(department);

        Condition condition = new Condition()
                .withComparisonOperator(ComparisonOperator.BEGINS_WITH)
                .withAttributeValueList(new AttributeValue().withS(MIN_URGENCY));

        var queryExpression =
                new DynamoDBQueryExpression<MessageType>()
                        .withLimit(pagination.getPageSize())
                        .withHashKeyValues(messageType)
                        .withRangeKeyCondition("urgencyType", condition);

        if(pagination.getNext() != null){
            String[] next = pagination.getNext().split("\\*");

            Map<String, AttributeValue> exclusiveStartKey = new HashMap<>();
            exclusiveStartKey.put("department", new AttributeValue().withS(next[0]));
            exclusiveStartKey.put("urgencyType", new AttributeValue().withS(next[1]));

            queryExpression.setExclusiveStartKey(exclusiveStartKey);
        }


        return mapper.queryPage(MessageType.class, queryExpression);
    }



}

