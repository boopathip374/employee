package com.employee.service;

import java.util.List;
import java.util.Optional;
import com.employee.domain.*;
import com.employee.exception.ApiException;
import com.employee.model.RequestEmployeeService;
import com.employee.model.Response;

public interface EmployeeService {
	
	public Response create(RequestEmployeeService requestEmployeeService) throws ApiException;
	public Optional<EmployeeEntity> Read(String id) throws ApiException;
	public Response update(RequestEmployeeService requestEmployeeService) throws ApiException;
	public void delete(String Id) throws ApiException;
	public List<EmployeeEntity> ReadAll() throws ApiException;
	public void deleteAll() throws ApiException;

}
