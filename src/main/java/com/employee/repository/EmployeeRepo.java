package com.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.employee.domain.EmployeeEntity;

public interface EmployeeRepo extends MongoRepository<EmployeeEntity, String>{

}
