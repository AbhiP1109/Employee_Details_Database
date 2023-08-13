package com.qsp.springboot_employee.exception;

public class EmptyEmployeeTableException extends RuntimeException{

	private String message;

	@Override
	public String getMessage() {

		return message;
	}
	public EmptyEmployeeTableException(String messgae) {
		this.message = messgae;
	}
}
