package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExpControllerAdvice {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setType("ERROR");
		errorResponse.setCode(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		errorResponse.setDetails(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ErrorResponse> apiExceptionHandler(ApiException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setType(ex.getType());
		errorResponse.setCode(ex.getCode());
		errorResponse.setDetails(ex.getDetails());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.valueOf(Integer.parseInt(ex.getCode())));
		
	}

}
