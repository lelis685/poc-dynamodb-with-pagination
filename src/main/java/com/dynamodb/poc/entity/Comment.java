package com.dynamodb.poc.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.dynamodb.poc.utils.LocalDateTimeConverter;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@DynamoDBTable(tableName = "Comments")
public class Comment {
    @DynamoDBHashKey
    private String itemId;

    @DynamoDBRangeKey
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "UserID-index")
    private LocalDateTime time;

    private String message;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "UserID-index")
    private String userId;

    @DynamoDBIndexRangeKey(localSecondaryIndexName = "Rating-index")
    private int rating;

}
