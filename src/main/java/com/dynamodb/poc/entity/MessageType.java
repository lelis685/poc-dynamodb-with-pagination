package com.dynamodb.poc.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

@Data
@DynamoDBTable(tableName = "MessageType")
public class MessageType {

    @DynamoDBHashKey
    private String department; //

    @DynamoDBRangeKey
    private String urgencyType;

    private String message;

}
