package com.qsp.springboot_employee.exception;

public class PhoneNumberNotFoundException extends RuntimeException{

	private String message;

	@Override
	public String getMessage() {

		return message;
	}
	public PhoneNumberNotFoundException(String message) {

		this.message=message;
	}
}
