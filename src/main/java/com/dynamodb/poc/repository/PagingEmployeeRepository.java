package com.dynamodb.poc.repository;

import com.dynamodb.poc.entity.Employee;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagingEmployeeRepository extends PagingAndSortingRepository<Employee, String> {

	@EnableScan
	@EnableScanCount
	Page<Employee> findAll(Pageable pageable);

}