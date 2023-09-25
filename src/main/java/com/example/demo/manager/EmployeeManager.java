package com.example.demo.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.Employee;
import com.example.demo.exception.EmployeeNotFoundException1;
import com.example.demo.repository.SampleRepository;

@Component
public class EmployeeManager {

	@Autowired
	public SampleRepository repo;

	// hello world
	public String getresponse() {

		return repo.get();
	}

	// all employee
	public List<Employee> getEmployee() {
		List<Employee> emplist=new ArrayList<Employee>();

		try {
			emplist= repo.getEmployee();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;		}
		return emplist;
	}

	// employee by id
	public Employee empByID(int id) {
		Employee emp=new Employee();

		try {
			emp=repo.getEmployeeById(id);
		} catch (Exception e) {
			
			throw new EmployeeNotFoundException1("Employee not found with id->"+id);
		}
		return emp;
	}

	// save employee
	public Employee savEmp(Employee employee) {
		Employee emp=new Employee();

		try {
			emp= repo.saveEmployee(employee);
		} catch (Exception e) {
			throw new EmployeeNotFoundException1("Employee save failed");
		}
		return emp;
	}

	// update employee
	public Employee updEmp(Employee employee) {
		Employee emp=new Employee();

		try {
			if(repo.getEmployeeById(employee.getId())!=null) {
				emp= repo.updateEmployee(employee);
			}
			
		} catch (Exception e) {
			throw new EmployeeNotFoundException1("Employee not found with id "+ employee.getId());
		}
		return emp;
	}

	// delete employee
	public int delEmp(int id) {
		
		try {
			if(repo.getEmployeeById(id)!=null) {
				repo.deleteEmployee(id);
			}
			
		} catch (Exception e) {
			throw new EmployeeNotFoundException1("Employee not found with id->"+id);
		}return id;
		 
	}
}
