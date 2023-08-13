package com.qsp.springboot_employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.springboot_employee.dao.EmployeeDao;
import com.qsp.springboot_employee.dto.Employee;
import com.qsp.springboot_employee.service.EmployeeService;
import com.qsp.springboot_employee.util.ResponseStructure;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeDao dao;
	
	@Autowired
	private EmployeeService service;

	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@RequestBody Employee employee) {

		return service.saveEmployee(employee);
	}
	@GetMapping("/findAll")
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeeById() {

		return service.findAllEmployee();
	}

	@GetMapping("/find")
	public ResponseEntity<ResponseStructure<Employee>> fetchEmployeeById(@RequestParam int id) {

		return service.fetchEmployeeById(id);
	}
	
//	@DeleteMapping("/delete/{id}")
//	public Employee deleteByID(@PathVariable int id) {
//		
//		return dao.deleteByID(id);
//	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<Employee>> deleteByID(@RequestParam int id) {
		
		return service.deleteByID(id);
	}
	
	@PutMapping("/updateAll")
	public ResponseEntity<ResponseStructure<Employee>> updateAll(@RequestParam int id,@RequestBody Employee employee) {
		
	  return service.updateAll(id, employee);
	}
	@PatchMapping("/updatePhoneByID")
	public ResponseEntity<ResponseStructure<Employee>> updateByID(@RequestParam int id,@RequestParam long phone) {
		
	  return service.updateByID(id,phone);
	}
	@PatchMapping("/updateEmail")
	public ResponseEntity<ResponseStructure<Employee>> updateEmail(@RequestParam int id,@RequestParam String email) {
		
	  return service.updateEmail(id,email);
	}
	@PatchMapping("/updateSalary")
	public ResponseEntity<ResponseStructure<Employee>> updateSalary(@RequestParam int id,@RequestParam double salary) {
		
	  return service.updateSalary(id,salary);
	}
	@GetMapping("/findEmployeeByEmail")
	public ResponseEntity<ResponseStructure<Employee>> findEmployeeByEmail(@RequestParam String email) {

		return service.findEmployeeByEmail(email);
	}
	
	@GetMapping("/findEmployeeByPhone")
	public ResponseEntity<ResponseStructure<Employee>> findEmployeeByPhone(@RequestParam long phone) {

		return service.findEmployeeByPhone(phone);
	}
	
	@GetMapping("/findEmployeeBySalaryGreaterThan")
	public ResponseEntity<ResponseStructure<List<Employee>>> findBySalaryGreaterThan(@RequestParam double salary) {

		return service.findEmployeeBySalaryGreaterThan(salary);
	}
	@GetMapping("/findEmployeeBySalaryLessThan")
	public ResponseEntity<ResponseStructure<List<Employee>>> findBySalaryLessThan(@RequestParam double salary) {

		return service.findBySalaryLessThan(salary);
	}
		
}
