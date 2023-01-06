package com.dynamodb.poc.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.dynamodb.poc.entity.Comment;
import com.dynamodb.poc.entity.SupportMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Repository
public class CommentsRepository {

    private final DynamoDBMapper mapper;


    public List<Comment> allForItemWithMinRating(String itemId, int minRating) {

        Comment comment = new Comment();
        comment.setItemId(itemId);

        Condition condition = new Condition()
                .withComparisonOperator(ComparisonOperator.GE)
                .withAttributeValueList(new AttributeValue().withN(Integer.toString(minRating)));

        DynamoDBQueryExpression<Comment> queryExpression
                = new DynamoDBQueryExpression<Comment>()
                .withHashKeyValues(comment)
                .withRangeKeyCondition(
                        "rating",
                        condition
                )
                .withScanIndexForward(false);

        return mapper.query(Comment.class, queryExpression);
    }

    public List<Comment> allForUser(String userId) {
        Comment comment = new Comment();
        comment.setUserId(userId);

        DynamoDBQueryExpression<Comment> queryExpression
                = new DynamoDBQueryExpression<Comment>()
                .withHashKeyValues(comment)
                .withConsistentRead(false)
                .withScanIndexForward(false);

        return mapper.query(Comment.class, queryExpression);
    }


}
