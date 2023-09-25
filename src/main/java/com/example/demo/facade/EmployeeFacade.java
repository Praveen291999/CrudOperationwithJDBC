package com.example.demo.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.Employee;
import com.example.demo.dto.User;
import com.example.demo.exception.EmployeeNotFoundException1;
import com.example.demo.manager.EmployeeManager;

@Component
public class EmployeeFacade {

	@Autowired
	public EmployeeManager manager;

	// to display hello world
	public String getRes() {
		return manager.getresponse();
	}

	// to get all employess
	public List<Employee> getEmp() {
		List<Employee> emplist = new ArrayList<Employee>();
		emplist = manager.getEmployee();
		return emplist;
	}

	// to get employee by id
	public Employee getEmpByID(int id) {
		Employee emp = new Employee();

		emp = manager.empByID(id);

		return emp;
	}

	// to (save employee)
	public Employee getSavEmp(Employee employee) {
		Employee emp = new Employee();
		emp = manager.savEmp(employee);
		return emp;
	}

	// update
	public Employee getupdEmp(Employee employee) {
		Employee emp = new Employee();
		emp = manager.updEmp(employee);
		return emp;
	}

	// delete
	public int getdelEmp(int id) {
		manager.delEmp(id);
		return id;
	}

	public User getUser() {
		User user=User.builder().name("Sam").address("Chennai").age(25).build();
		return user;
	}

}
