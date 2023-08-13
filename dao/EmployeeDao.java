package com.qsp.springboot_employee.dao;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.qsp.springboot_employee.dto.Employee;
import com.qsp.springboot_employee.repo.EmployeeRepo;

@Repository
public class EmployeeDao {

	@Autowired
	private EmployeeRepo employeeRepo;

	public Employee findEmployeeById(int id) {

//		return employeeRepo.findById(id).get();// If 
//		
//		Optional<Employee> optional = employeeRepo.findById(id);
//		if (optional.isPresent()) {
//			return optional.get();
//		}
//		return null;
		
		try {
			return employeeRepo.findById(id).get();
			
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	public Employee deleteByID(int id) {
		
//		Optional<Employee> optional = employeeRepo.findById(id);
//		if (optional.isPresent()) {
////			Employee employee = optional.get();
//			employeeRepo.deleteById(id);
//			return optional.get();
//		}
//		return null;
		
		try {
			Optional<Employee> optional = employeeRepo.findById(id);
			employeeRepo.deleteById(id);
			return optional.get();
			
		} catch (Exception e) {
			return null;
		}
		
	}
	public Employee updateAll(int id, Employee employee) {
		
		Optional<Employee> optional = employeeRepo.findById(id);
		
		if (optional.isPresent()) {
			employee.setId(id);
			return employeeRepo.save(employee);
		} 
		return null;
	}
	
	public List<Employee> findAllEmployee() {
		
		return employeeRepo.findAll();
	}

	public Employee saveEmployee(Employee employee) {

		return employeeRepo.save(employee);
	}
	public Employee findEmployeeByEmail(String email) {
		
			return employeeRepo.findByEmail(email);
		
	}
	public List<Employee> findEmployeeBySalaryGreaterThan(double salary) {
	
		return employeeRepo.findBySalaryGreaterThan(salary);
	}
	public List<Employee> findBySalaryLessThan(double salary) {
		
		return employeeRepo.findBySalaryLessThan(salary);
	}
	public Employee findEmployeeByPhone(long phone) {
		
		return employeeRepo.findByPhone(phone);
	}
}
