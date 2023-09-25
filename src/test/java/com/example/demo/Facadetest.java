package com.example.demo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dto.Employee;
import com.example.demo.exception.EmployeeNotFoundException1;
import com.example.demo.facade.EmployeeFacade;
import com.example.demo.manager.EmployeeManager;

@SpringBootTest
@RunWith(SpringRunner.class)
class Facadetest {

	@InjectMocks
	public EmployeeFacade facad;

	@Mock
	EmployeeManager manag;

	@Test
	public void StringTest() {
		String ss = "HelloWorld";
		when(manag.getresponse()).thenReturn(ss);
		assertEquals("HelloWorld", facad.getRes());
	}

	@Test
	public void savTest() throws Exception {
		Employee emp = new Employee();
		emp.setId(6);
		emp.setName("Dhoni");
		emp.setAddress("Chennai");
		emp.setGender("Male");

		when(manag.savEmp(emp)).thenReturn(emp);
		assertEquals(emp.getId(), facad.getSavEmp(emp).getId());
	}

	@Test
	public void getEmployeeTest() {
		when(manag.getEmployee()).thenReturn(
				Stream.of(new Employee(2, "kumar", "ahmedabad", "Male"), new Employee(3, "sachin", "Mumbai", "Male"))
						.collect(Collectors.toList()));

		assertEquals(200, facad.getEmp().size());

	}

	@Test
	public void getEmp() throws Exception {
		Employee emp = new Employee();
		emp.setId(6);
		emp.setName("Dhoni");
		emp.setAddress("Chennai");
		emp.setGender("Male");

		when(manag.empByID(6)).thenReturn(emp);

		assertEquals(emp, facad.getEmpByID(6));
	}

	@Test
	public void delEmpl() throws Exception {
		Employee emp = new Employee(4, "Ram", "Chennai", "Male");
		facad.getdelEmp(emp.getId());
		verify(manag, times(1)).delEmp(emp.getId());

	}

	@Test
	public void updEmpTest() throws Exception {
		Employee empl = new Employee(9, "Vijay", "Chennai", "Male");

		when(manag.updEmp(empl)).thenReturn(empl);
		assertEquals(empl, facad.getupdEmp(empl));
	}

	// Exception get employee by id
	@Test
	public void getEmployeeByIdTestException() throws Exception {

		when(manag.empByID(88)).thenThrow(EmployeeNotFoundException1.class);

		assertThatThrownBy(() -> facad.getEmpByID(88)).isInstanceOf(EmployeeNotFoundException1.class);
	}

	// Exception del employee by id
	@Test
	public void delEmployeeByIdTestException() throws Exception {

		when(manag.delEmp(88)).thenThrow(EmployeeNotFoundException1.class);

		assertThatThrownBy(() -> facad.getdelEmp(88)).isInstanceOf(EmployeeNotFoundException1.class);
	}

	// Exception get all employee
	@Test
	public void getEmployeeTestException() throws Exception {

		when(manag.getEmployee()).thenThrow(EmployeeNotFoundException1.class);

		assertThatThrownBy(() -> facad.getEmp()).isInstanceOf(EmployeeNotFoundException1.class);
	}

	// Exception update employee
	@Test
	public void updEmployeeTestException() throws Exception {
		Employee emp1 = new Employee(9, "Vijay", "Chennai", "Male");

		when(manag.updEmp(emp1)).thenThrow(EmployeeNotFoundException1.class);

		assertThatThrownBy(() -> facad.getupdEmp(emp1)).isInstanceOf(EmployeeNotFoundException1.class);
	}

	// Exception save  employee
	@Test
	public void savEmployeeTestException() throws Exception {
		Employee emp = new Employee();
		emp.setId(6);
		emp.setName("Dhoni");
		emp.setAddress("Chennai");
		emp.setGender("Male");

		when(manag.savEmp(emp)).thenThrow(EmployeeNotFoundException1.class);

		assertThatThrownBy(() -> facad.getSavEmp(emp)).isInstanceOf(EmployeeNotFoundException1.class);
	}

}

