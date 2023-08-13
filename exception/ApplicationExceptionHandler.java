package com.qsp.springboot_employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.qsp.springboot_employee.util.ResponseStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	ResponseStructure<String> structure = new ResponseStructure<>();
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> idNotFoundExceptionHandler(IdNotFoundException exception) {
		
		structure.setMessage("ID not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> emailNotFoundExceptionHandler(EmailNotFoundException exception) {
		
		structure.setMessage("Email ID not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(PhoneNumberNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> phoneNumberNotFoundExceptionHandler(PhoneNumberNotFoundException exception) {
		
		structure.setMessage("Phone Number not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(EmptyEmployeeTableException.class)
	public ResponseEntity<ResponseStructure<String>> emptyEmployeeTableExceptionHandler(EmptyEmployeeTableException exception) {
		
		structure.setMessage("Employee Table is empty");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);
	}
	
}
