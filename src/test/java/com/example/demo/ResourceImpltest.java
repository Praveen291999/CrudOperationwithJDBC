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
import com.example.demo.resource.EmployeeResourceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
class ResourceImpltest {

	@InjectMocks
	public EmployeeResourceImpl resImpl;

	@Mock
	EmployeeFacade facade;

	@Test
	public void StringTest() {
		String ss = "HelloWorld";
		when(facade.getRes()).thenReturn(ss);
		assertEquals("HelloWorld", resImpl.getResponse());
	}

	@Test
	public void saveEmployeetest() {
		Employee emp = new Employee();
		emp.setId(6);
		emp.setName("Dhoni");
		emp.setAddress("Chennai");
		emp.setGender("Male");

		when(facade.getSavEmp(emp)).thenReturn(emp);

		assertEquals(emp.getId(), resImpl.addEmployee(emp).getId());

	}

	@Test
	public void getEmployeeTest() {
		when(facade.getEmp()).thenReturn(
				Stream.of(new Employee(2, "kumar", "ahmedabad", "Male"), new Employee(3, "sachin", "Mumbai", "Male"))
						.collect(Collectors.toList()));

		assertEquals(2, resImpl.getEmployee().size());

	}

	@Test
	public void getEmp() {
		Employee emp = new Employee();
		emp.setId(6);
		emp.setName("Dhoni");
		emp.setAddress("Chennai");
		emp.setGender("Male");

		when(facade.getEmpByID(6)).thenReturn(emp);

		assertEquals(emp, resImpl.getEmpById(6));
	}

	@Test
	public void delEmpl() {
		Employee emp = new Employee(4, "Ram", "Chennai", "Male");
		resImpl.deleteEmp(emp.getId());
		verify(facade, times(1)).getdelEmp(emp.getId());

	}

	@Test
	public void updEmpTest() {
		Employee empl = new Employee(9, "Vijay", "Chennai", "Male");

		when(facade.getupdEmp(empl)).thenReturn(empl);
		assertEquals(empl, resImpl.updateEmp(empl));
	}

	// Exception get employee by id
	@Test
	public void getEmployeeByIdTestException() throws Exception {

		when(facade.getEmpByID(4)).thenThrow(EmployeeNotFoundException1.class);

		assertThatThrownBy(() -> resImpl.getEmpById(4)).isInstanceOf(EmployeeNotFoundException1.class);
	}

	// Exception del employee by id
	@Test
	public void delEmployeeByIdTestException() throws Exception {

		when(facade.getdelEmp(88)).thenThrow(EmployeeNotFoundException1.class);

		assertThatThrownBy(() -> resImpl.deleteEmp(88)).isInstanceOf(EmployeeNotFoundException1.class);
	}

	// Exception get all employee
	@Test
	public void getEmployeeTestException() throws Exception {

		when(facade.getEmp()).thenThrow(EmployeeNotFoundException1.class);

		assertThatThrownBy(() -> resImpl.getEmployee()).isInstanceOf(EmployeeNotFoundException1.class);
	}

	// Exception update employee
	@Test
	public void updEmployeeTestException() throws Exception {
		Employee emp1 = new Employee(9, "Vijay", "Chennai", "Male");

		when(facade.getupdEmp(emp1)).thenThrow(EmployeeNotFoundException1.class);

		assertThatThrownBy(() -> resImpl.updateEmp(emp1)).isInstanceOf(EmployeeNotFoundException1.class);
	}

	// Exception save employee
	@Test
	public void savEmployeeTestException() throws Exception {
		Employee emp = new Employee();
		emp.setId(6);
		emp.setName("Dhoni");
		emp.setAddress("Chennai");
		emp.setGender("Male");

		when(facade.getSavEmp(emp)).thenThrow(EmployeeNotFoundException1.class);

		assertThatThrownBy(() -> resImpl.addEmployee(emp)).isInstanceOf(EmployeeNotFoundException1.class);
	}

}
