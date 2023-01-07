package com.dynamodb.poc.entity.data;

import com.dynamodb.poc.repository.PagingEmployeeRepository;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageableResultSet<T> {

    private T data;
    private Pagination pagination;

}
