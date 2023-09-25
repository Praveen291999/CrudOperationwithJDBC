package com.example.demo.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.dto.Employee;
import com.example.demo.dto.User;
import com.example.demo.exception.EmployeeNotFoundException1;
import com.example.demo.exception.ErrorObject;
import com.example.demo.facade.EmployeeFacade;

@Component
public class EmployeeResourceImpl implements EmployeeResource {

	@Autowired
	public EmployeeFacade facade;

	// hello world
	@Override
	public String getResponse() {

		return facade.getRes();

	}

	// get all employees
	@Override
	public List<Employee> getEmployee() {

		List<Employee> emplist = new ArrayList<Employee>();
		emplist = facade.getEmp();
		return emplist;
	}

	// get employee by id
	@Override
	public Employee getEmpById(int id) {
		Employee emp = new Employee();
		emp = facade.getEmpByID(id);
		return emp;
	}

	// add employee
	@Override
	public Employee addEmployee(Employee employee) {
		Employee emp = new Employee();
		emp = facade.getSavEmp(employee);
		return emp;
	}

	// update employee
	@Override
	public Employee updateEmp(Employee employee) {
		Employee emp = new Employee();
		emp = facade.getupdEmp(employee);
		return emp;
	}

	// delete
	@Override
	public int deleteEmp(int id) {

		facade.getdelEmp(id);
		return id;

	}

	@Override
	public ResponseEntity<ErrorObject> handleException(EmployeeNotFoundException1 ex) {

		ErrorObject eObject = new ErrorObject(HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				System.currentTimeMillis());

		return new ResponseEntity<ErrorObject>(eObject, HttpStatus.NOT_FOUND);
	}

	@Override
	public User getUser() {
		
		
		return  facade.getUser();
	}

}
