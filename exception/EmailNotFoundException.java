package com.qsp.springboot_employee.exception;

public class EmailNotFoundException extends RuntimeException {

	private String message;

	@Override
	public String getMessage() {

		return message;
	}
	public EmailNotFoundException(String message) {
		this.message=message;
	}
}
