package com.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.employee.domain.EmployeeEntity;

@Repository
public interface EmployeeRepo extends MongoRepository<EmployeeEntity, String>{

}
