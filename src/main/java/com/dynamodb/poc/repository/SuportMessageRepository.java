package com.dynamodb.poc.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.dynamodb.poc.entity.SupportMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Repository
public class SuportMessageRepository {

    private final DynamoDBMapper mapper;


    public List<SupportMessage> getAllForDepartment(String department) {
        var queryExpression =
                new DynamoDBQueryExpression<SupportMessage>()
                        .withScanIndexForward(false)
                        .withKeyConditionExpression("department = :department")
                        .withExpressionAttributeValues(Map.of(
                                ":department", new AttributeValue().withS(department)
                        ));

        return mapper.query(SupportMessage.class, queryExpression);

    }


    public List<SupportMessage> getAllForDepartmentOnDay(String department, String day) {

        var queryExpression =
                new DynamoDBQueryExpression<SupportMessage>()
                        .withKeyConditionExpression("department = :department AND begins_with(#time, :day)")
                        .withExpressionAttributeNames(Map.of(  // mapping for reserved keywords in dynamodb
                                "#time", "time")
                        ).withExpressionAttributeValues(Map.of(
                                ":department", new AttributeValue().withS(department),
                                ":day", new AttributeValue().withS(day)
                        ));
        return mapper.query(SupportMessage.class, queryExpression);
    }


}
