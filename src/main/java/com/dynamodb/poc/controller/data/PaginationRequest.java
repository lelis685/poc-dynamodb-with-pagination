package com.dynamodb.poc.controller.data;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;



@Data
public class PaginationRequest {

    private  final Integer DEFAULT_PAGE_SIZE = 2;

    private String next;

    @Max(value = 10)
    private int pageSize = DEFAULT_PAGE_SIZE;




}
