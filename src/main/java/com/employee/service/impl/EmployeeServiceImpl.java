package com.employee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.constant.EmployeeConstants;
import com.employee.domain.EmployeeEntity;
import com.employee.exception.ApiException;
import com.employee.model.RequestEmployeeService;
import com.employee.model.Response;
import com.employee.repository.EmployeeRepo;
import com.employee.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepo employeeRepository;
	
	@Override
	public Response create(RequestEmployeeService requestEmployeeService) throws ApiException {
		try {
			employeeRepository.save(new EmployeeEntity(requestEmployeeService.getName(), requestEmployeeService.getAge(), requestEmployeeService.getMobile(),requestEmployeeService.getAddress()));
			return new Response(EmployeeConstants.CREATED, EmployeeConstants.SUCCESS);
		}catch (Exception ex) {
			throw new ApiException(EmployeeConstants.ERROR_TYPE,EmployeeConstants.ERROR_CODE_500,ex.getMessage());
		}
	}

	@Override
	public Optional<EmployeeEntity> Read(String id) throws ApiException {
		try {
			Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
			return employeeEntity;
		}catch(Exception ex) {
			throw new ApiException(EmployeeConstants.ERROR_TYPE,EmployeeConstants.ERROR_CODE_500,ex.getMessage());
		}
	}

	@Override
	public Response update(RequestEmployeeService requestEmployeeService) throws ApiException {
		try {
			 Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(requestEmployeeService.getId());
			  if (employeeEntity.isPresent()) {
				  EmployeeEntity existingEmployeeEntity = employeeEntity.get();
				  if(StringUtils.isNotBlank(requestEmployeeService.getName())) {
					  existingEmployeeEntity.setName(requestEmployeeService.getName());
				  }
				  if(StringUtils.isNotBlank(requestEmployeeService.getAge())) {
					  existingEmployeeEntity.setAge(requestEmployeeService.getAge());
				  }
				  if(StringUtils.isNotBlank(requestEmployeeService.getMobile())) {
					  existingEmployeeEntity.setMobile(requestEmployeeService.getMobile());
				  }
				  if(StringUtils.isNotBlank(requestEmployeeService.getAddress())) {
					  existingEmployeeEntity.setAddress(requestEmployeeService.getAddress());
				  }
				  employeeRepository.save(existingEmployeeEntity);
				  return new Response(EmployeeConstants.UPDATED, EmployeeConstants.SUCCESS);
			  } else {
			    return new Response(EmployeeConstants.NOT_FOUND, EmployeeConstants.FAILED);
			  }
		}catch (Exception ex) {
			throw new ApiException(EmployeeConstants.ERROR_TYPE,EmployeeConstants.ERROR_CODE_500,ex.getMessage());
		}
	}

	@Override
	public void delete(String id) throws ApiException {
		try {
			employeeRepository.deleteById(id);
		}catch(Exception ex) {
			throw new ApiException(EmployeeConstants.ERROR_TYPE,EmployeeConstants.ERROR_CODE_500,ex.getMessage());
		}
		
	}

	
	@Override
	public List<EmployeeEntity> ReadAll() throws ApiException {
		List<EmployeeEntity> employeeEntity = new ArrayList<EmployeeEntity>();
		try {
			employeeRepository.findAll().forEach(employeeEntity::add);
	        return employeeEntity;
		}catch(Exception ex) {
			throw new ApiException(EmployeeConstants.ERROR_TYPE,EmployeeConstants.ERROR_CODE_500,ex.getMessage());
		}
		
	}

	

	@Override
	public void deleteAll() throws ApiException {
		try {
			employeeRepository.deleteAll();
		}catch(Exception ex) {
			throw new ApiException(EmployeeConstants.ERROR_TYPE,EmployeeConstants.ERROR_CODE_500,ex.getMessage());
		}
	}

}
