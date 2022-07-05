package com.employee.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.employee.constant.EmployeeConstants;
import com.employee.exception.ErrorResponse;
import com.employee.model.RequestEmployeeService;
import org.apache.commons.lang3.StringUtils;

@Component
public class EmployeeServiceValidation {

	/**
	 * 
	 * @param requestEmployeeService
	 * @return
	 */
	public ResponseEntity<ErrorResponse> validateEmployeeRequest(RequestEmployeeService requestEmployeeService){
		if(requestEmployeeService == null) {
			ErrorResponse errorResponse = getErrorResponse(EmployeeConstants.ERROR_TYPE,EmployeeConstants.ERROR_CODE_500,EmployeeConstants.REQUEST_OBJECT_IS_NULL);
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(StringUtils.isBlank(requestEmployeeService.getName()) && StringUtils.isBlank(requestEmployeeService.getAge())) {
			ErrorResponse errorResponse = getErrorResponse(EmployeeConstants.ERROR_TYPE,EmployeeConstants.ERROR_CODE_422,EmployeeConstants.VALIDATION_NAME);
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return null;
		
	}
	
	private ErrorResponse getErrorResponse(String type, String code, String details) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setType(type);
		errorResponse.setCode(code);
		errorResponse.setDetails(details);
		return errorResponse;
		
	}

	/**
	 * 
	 * @param id
	 * @param requestEmployeeService
	 * @return
	 */
	public ResponseEntity<ErrorResponse> validateUpdateEmployeeRequest(RequestEmployeeService requestEmployeeService) {
		if(requestEmployeeService == null || StringUtils.isBlank(requestEmployeeService.getId())) {
			ErrorResponse errorResponse = getErrorResponse(EmployeeConstants.ERROR_TYPE,EmployeeConstants.ERROR_CODE_500,EmployeeConstants.REQUEST_OBJECT_IS_NULL);
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(StringUtils.isBlank(requestEmployeeService.getName()) && StringUtils.isBlank(requestEmployeeService.getAge()) &&
				StringUtils.isBlank(requestEmployeeService.getMobile()) && StringUtils.isBlank(requestEmployeeService.getAddress())) {
			ErrorResponse errorResponse = getErrorResponse(EmployeeConstants.ERROR_TYPE,EmployeeConstants.ERROR_CODE_422,EmployeeConstants.VALIDATION_ALL_FIELDS);
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param id
	 * @param requestEmployeeService
	 * @return
	 */
	public ResponseEntity<ErrorResponse> validateEmployeeRequestId(String id) {
		if(StringUtils.isBlank(id)) {
			ErrorResponse errorResponse = getErrorResponse(EmployeeConstants.ERROR_TYPE,EmployeeConstants.ERROR_CODE_500,EmployeeConstants.REQUEST_ID_IS_NULL);
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}

}
