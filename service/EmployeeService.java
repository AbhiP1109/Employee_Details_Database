package com.qsp.springboot_employee.service;

import java.util.List;

import org.apache.catalina.loader.ResourceEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.springboot_employee.dao.EmployeeDao;
import com.qsp.springboot_employee.dto.Employee;
import com.qsp.springboot_employee.exception.EmailNotFoundException;
import com.qsp.springboot_employee.exception.EmptyEmployeeTableException;
import com.qsp.springboot_employee.exception.IdNotFoundException;
import com.qsp.springboot_employee.exception.PhoneNumberNotFoundException;
import com.qsp.springboot_employee.util.ResponseStructure;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao dao;

	private ResponseStructure<Employee> structure = new ResponseStructure<>();
	private ResponseStructure<List<Employee>> structure1 = new ResponseStructure<>();

	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee) {

		double sal = employee.getSalary();

		if (sal <= 10000) {
			employee.setGrade('A');
		} else if (sal > 10000 && sal <= 20000) {
			employee.setGrade('B');
		} else if (sal > 20000 && sal <= 40000) {
			employee.setGrade('C');
		} else if (sal > 40000) {
			employee.setGrade('D');
		}

//		return dao.saveEmployee(employee);
		structure.setMessage("Save Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveEmployee(employee));

		return new ResponseEntity<>(structure,HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findAllEmployee() {

		List<Employee> list = dao.findAllEmployee();

		if (!(list.isEmpty())) {
			structure1.setMessage("Data found Successfully");
			structure1.setStatus(HttpStatus.FOUND.value());
			structure1.setData(list);
			return new ResponseEntity<>(structure1,HttpStatus.FOUND);
		} else {
//			structure1.setMessage("No employee present in database");
//			structure1.setStatus(HttpStatus.NOT_FOUND.value());
//			structure1.setData(null);
//			return new ResponseEntity<>(structure1,HttpStatus.NOT_FOUND);

			throw new EmptyEmployeeTableException("No Employee present is Database");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> fetchEmployeeById(int id) {

		Employee employee = dao.findEmployeeById(id);

		if (employee != null) {
			structure.setMessage("Data found Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<>(structure,HttpStatus.FOUND);
		} else {
//			structure.setMessage("ID not found in database");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);

			throw new IdNotFoundException("Employee ID not found");
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> deleteByID(int id) {

		Employee employee = dao.findEmployeeById(id);

		if (employee != null) {
			dao.deleteByID(id);
			structure.setMessage("Data Deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(employee);
			return new ResponseEntity<>(structure,HttpStatus.OK);
		} else {
//			structure.setMessage("ID not found in database");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);

			throw new IdNotFoundException("Employee ID not found");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateByID(int id, long phone) {

		Employee employee = dao.findEmployeeById(id);

		if (employee != null) {
			employee.setPhone(phone);
			return updateAll(id, employee);
		} else {
//			structure.setMessage("ID not found in database");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);

			throw new IdNotFoundException("Employee ID not found");
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> updateAll(int id, Employee employee) {

		Employee dbemployee = dao.findEmployeeById(id);

		if (dbemployee != null) {

			double sal = employee.getSalary();

			if (sal <= 10000) {
				employee.setGrade('A');
			} else if (sal > 10000 && sal <= 20000) {
				employee.setGrade('B');
			} else if (sal > 20000 && sal <= 40000) {
				employee.setGrade('C');
			} else if (sal > 40000) {
				employee.setGrade('D');
			}
			structure.setMessage("Data Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.updateAll(id, employee));
			return new ResponseEntity<>(structure,HttpStatus.OK);
		} else {
//			structure.setMessage("ID not found in database");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);

			throw new IdNotFoundException("Employee ID not found");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmail(int id, String email) {

		Employee employee = dao.findEmployeeById(id);

		if (employee != null) {
			employee.setEmail(email);
			return updateAll(id, employee);
		} else {
//			structure.setMessage("ID not found in database");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);

			throw new IdNotFoundException("Employee ID not found");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateSalary(int id, double salary) {
		Employee employee = dao.findEmployeeById(id);

		if (employee != null) {
			employee.setSalary(salary);
			return updateAll(id, employee);
		} else {
//			structure.setMessage("ID not found in database");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);

			throw new IdNotFoundException("Employee ID not found");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> findEmployeeByEmail(String email) {

		Employee employee = dao.findEmployeeByEmail(email);

		if (employee != null) {
			structure.setMessage("Data found Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<>(structure,HttpStatus.FOUND);
		} else {
//			structure.setMessage("ID not found in database");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);

			throw new EmailNotFoundException("Email ID not present in database");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeeBySalaryGreaterThan(double salary) {
		List<Employee> employees = dao.findEmployeeBySalaryGreaterThan(salary);

		if (!(employees.isEmpty())) {
			structure1.setMessage("Data found Successfully");
			structure1.setStatus(HttpStatus.FOUND.value());
			structure1.setData(employees);
			return new ResponseEntity<>(structure1,HttpStatus.FOUND);
		} else {
			structure1.setMessage("ID not found in database");
			structure1.setStatus(HttpStatus.NOT_FOUND.value());
			structure1.setData(null);
			return new ResponseEntity<>(structure1,HttpStatus.NOT_FOUND);

		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findBySalaryLessThan(double salary) {

		List<Employee> employees = dao.findBySalaryLessThan(salary);

		if (!(employees.isEmpty())) {
			structure1.setMessage("Data found Successfully");
			structure1.setStatus(HttpStatus.FOUND.value());
			structure1.setData(employees);
			return new ResponseEntity<>(structure1,HttpStatus.FOUND);
		} else {
			structure1.setMessage("ID not found in database");
			structure1.setStatus(HttpStatus.NOT_FOUND.value());
			structure1.setData(null);
			return new ResponseEntity<>(structure1,HttpStatus.NOT_FOUND);

		}
	}

	public ResponseEntity<ResponseStructure<Employee>> findEmployeeByPhone(long phone) {

		Employee employee =dao.findEmployeeByPhone(phone);

		if (employee != null) {
			structure.setMessage("Data found Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<>(structure,HttpStatus.FOUND);
		} else {
//			structure.setMessage("ID not found in database");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);

			throw new PhoneNumberNotFoundException("Phone number not presnt in database");
		}
	}
}
