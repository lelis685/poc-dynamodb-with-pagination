package com.dynamodb.poc.entity.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagination {

    private String next;

    private int pageSize;
}
