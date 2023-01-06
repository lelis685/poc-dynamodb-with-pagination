package com.dynamodb.poc.controller;

import com.dynamodb.poc.entity.Comment;
import com.dynamodb.poc.entity.SupportMessage;
import com.dynamodb.poc.repository.CommentsRepository;
import com.dynamodb.poc.repository.SuportMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final CommentsRepository commentsRepository;


    @GetMapping(params = "itemId")
    public List<Comment> allForItemWithMinRating(@RequestParam String itemId, @RequestParam int minRating) {
        return commentsRepository.allForItemWithMinRating(itemId, minRating);
    }


    @GetMapping
    public List<Comment> allForUser(@RequestParam String userId) {
        return commentsRepository.allForUser(userId);
    }


}
