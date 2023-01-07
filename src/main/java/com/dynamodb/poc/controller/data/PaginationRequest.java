package com.dynamodb.poc.controller.data;

import lombok.Data;

@Data
public class PaginationRequest {

    private String next;
    private int pageSize = 1;
}
