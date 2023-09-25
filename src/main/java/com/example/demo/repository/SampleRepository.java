package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Employee;

@Repository
public class SampleRepository {
	private static final String GET_EMPLOYEES_QUERY = "SELECT * FROM employee";
	private static final String INSERT_EMPLOYEE_QUERY = "INSERT INTO employee(id,name,address,gender) values(?,?,?,?)";
	private static final String UPDATE_Employee_BY_ID_QUERY = "UPDATE employee SET name=? WHERE ID=?";
	private static final String DELETE_Employee_BY_ID = "DELETE FROM employee WHERE ID=?";
	private static final String GET_Employee_BY_ID_QUERY = "SELECT id,name,address,gender FROM employee WHERE ID=?";
	private static final String GREETING="Hello from Employee Controller";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String get() {
		return GREETING;
	}

	// get all employees
	public List<Employee> getEmployee() {
		return jdbcTemplate.query(GET_EMPLOYEES_QUERY, (rs, rowNum) -> {
			return new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("gender"));
		});

	}

	// get employee by id
	public Employee getEmployeeById(int id) {
		return jdbcTemplate.queryForObject(GET_Employee_BY_ID_QUERY, (rs, rowNum) -> {
			return new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("gender"));
		}, id);
	}

	// create employee
	public Employee saveEmployee(Employee employee) {
		jdbcTemplate.update(INSERT_EMPLOYEE_QUERY, employee.getId(), employee.getName(), employee.getAddress(),
				employee.getGender());
		return employee;
	}

	// update employee
	public Employee updateEmployee(Employee employee) {
		jdbcTemplate.update(UPDATE_Employee_BY_ID_QUERY, employee.getName(), employee.getId());
		return employee;
	}

	// delete employee
	public int deleteEmployee(int id) {

		jdbcTemplate.update(DELETE_Employee_BY_ID, id);

		return id;

	}

}
