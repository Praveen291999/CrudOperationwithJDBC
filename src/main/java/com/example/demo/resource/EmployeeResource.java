package com.example.demo.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Employee;
import com.example.demo.dto.User;
import com.example.demo.exception.EmployeeNotFoundException1;
import com.example.demo.exception.ErrorObject;

@RestController
@RequestMapping("/api")

public interface EmployeeResource {

	// hello world
	@GetMapping("/hello")
	public String getResponse();

	// get all employees
	@GetMapping("/emps")
	public List<Employee> getEmployee();
	
	@GetMapping("/userdetails")
	public User getUser();

	// get employee by id
	@GetMapping("/emp/{id}")
	public Employee getEmpById(@PathVariable("id") int id);

	
	@PostMapping("/addemp")
	public Employee addEmployee(@RequestBody Employee employee);

	@PutMapping("/updemp")
	public Employee updateEmp(@RequestBody Employee employee);

	@DeleteMapping("/delemp/{id}")
	public int deleteEmp(@PathVariable("id") int id);
	
	//Exception Handling
	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleException(EmployeeNotFoundException1 ex);


	
	
}


