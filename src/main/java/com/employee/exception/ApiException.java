package com.employee.exception;




public class ApiException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String type;
	private String code;
	private String details;
	
	
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	public ApiException() {
		
	}
	/**
	 * @param type
	 * @param code
	 * @param details
	 */
	public ApiException(String type, String code, String details) {
		super();
		this.type = type;
		this.code = code;
		this.details = details;
	}

}
