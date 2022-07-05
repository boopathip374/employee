package com.employee.model;




public class Response {
	
	private String message;
	private String status;
	
	public Response() {
		
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @param message
	 * @param status
	 */
	public Response(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}
	
	
	

}
