package com.dynamodb.poc.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.dynamodb.poc.utils.LocalDateTimeConverter;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@DynamoDBTable(tableName = "SupportMessages")
public class SupportMessage {

    @DynamoDBHashKey
    private String department;

    @DynamoDBRangeKey
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime time;

    private int urgency;

    private String message;


}
