package com.qsp.springboot_employee.exception;

public class IdNotFoundException extends RuntimeException {

	private String message;
	
	@Override
	public String getMessage() {
		
		return message;
	}
	public IdNotFoundException(String message) {
		
		this.message = message;
	}

}
