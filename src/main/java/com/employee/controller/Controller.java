package com.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.constant.EmployeeConstants;
import com.employee.domain.EmployeeEntity;
import com.employee.exception.ApiException;
import com.employee.exception.ErrorResponse;
import com.employee.model.RequestEmployeeService;
import com.employee.model.Response;
import com.employee.service.EmployeeService;
import com.employee.utils.EmployeeServiceValidation;

@RestController
@RequestMapping("/api")
public class Controller {
	
	@Autowired
	private EmployeeServiceValidation employeeServiceValidation;
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createEmployee(@RequestBody RequestEmployeeService requestEmployeeService)throws ApiException {
		Response response = null;
		try {
			ResponseEntity<ErrorResponse> responseEntity = employeeServiceValidation.validateEmployeeRequest(requestEmployeeService);
			if (null != responseEntity) {
				return responseEntity;
			} else {
				response = employeeService.create(requestEmployeeService);
				return new ResponseEntity<Response>(response, HttpStatus.CREATED);
			}
		} catch (Exception ex) {
			throw new ApiException(EmployeeConstants.ERROR_TYPE,EmployeeConstants.ERROR_CODE_500,ex.getMessage());
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateEmployee(@RequestBody RequestEmployeeService requestEmployeeService)throws ApiException {
		Response response = null;
		try {
			ResponseEntity<ErrorResponse> responseEntity = employeeServiceValidation.validateUpdateEmployeeRequest(requestEmployeeService);
			if (null != responseEntity) {
				return responseEntity;
			} else {
				response = employeeService.update(requestEmployeeService);
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			}
		} catch (Exception ex) {
			throw new ApiException(EmployeeConstants.ERROR_TYPE,EmployeeConstants.ERROR_CODE_500,ex.getMessage());
		}
	}
	
	@GetMapping("/getemployee")
	public ResponseEntity<?> getAllEmployee() throws ApiException {
		List<EmployeeEntity> response = null;
		try {
				response = employeeService.ReadAll();
				if(response.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}else {
					return new ResponseEntity<List<EmployeeEntity>>(response, HttpStatus.OK);
				}
		} catch (Exception ex) {
			throw new ApiException(EmployeeConstants.ERROR_TYPE,EmployeeConstants.ERROR_CODE_500,ex.getMessage());
		}
	}
	
	@GetMapping("/getemployee/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable String id) throws ApiException {
		Optional<EmployeeEntity> response = null;
		try {
			ResponseEntity<ErrorResponse> responseEntity = employeeServiceValidation.validateEmployeeRequestId(id);
			if (null != responseEntity) {
				return responseEntity;
			}else {
				response = employeeService.Read(id);
				if(response.isPresent()) {
					return new ResponseEntity<>(response.get(), HttpStatus.OK);
				}else {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
		} catch (Exception ex) {
			throw new ApiException(EmployeeConstants.ERROR_TYPE,EmployeeConstants.ERROR_CODE_500,ex.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable String id) throws ApiException {
		try {
			ResponseEntity<ErrorResponse> responseEntity = employeeServiceValidation.validateEmployeeRequestId(id);
			if (null != responseEntity) {
				return responseEntity;
			} else {
				employeeService.delete(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteAllEmployee() throws ApiException {
		try {
				employeeService.deleteAll();
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			throw new ApiException(EmployeeConstants.ERROR_TYPE,EmployeeConstants.ERROR_CODE_500,ex.getMessage());
		}
	}


}
