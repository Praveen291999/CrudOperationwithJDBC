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
import com.example.demo.manager.EmployeeManager;
import com.example.demo.repository.SampleRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
class ManagerTest {

	@InjectMocks
	public EmployeeManager manag;

	@Mock
	SampleRepository sampRepo;

	@Test
	public void StringTest() {
		String ss = "HelloWorld";
		when(sampRepo.get()).thenReturn(ss);
		assertEquals("HelloWorld", manag.getresponse());
	}

	@Test
	public void savTest() {
		Employee emp = new Employee();
		emp.setId(6);
		emp.setName("Dhoni");
		emp.setAddress("Chennai");
		emp.setGender("Male");

		when(sampRepo.saveEmployee(emp)).thenReturn(emp);
		assertEquals(emp, manag.savEmp(emp));
	}

	@Test
	public void getEmployeeTest() {
		when(sampRepo.getEmployee()).thenReturn(
				Stream.of(new Employee(2, "kumar", "ahmedabad", "Male"), new Employee(3, "sachin", "Mumbai", "Male"))
						.collect(Collectors.toList()));

		assertEquals(2, manag.getEmployee().size());

	}

	@Test
	public void getEmp() {
		Employee emp = new Employee();
		emp.setId(6);
		emp.setName("Dhoni");
		emp.setAddress("Chennai");
		emp.setGender("Male");

		when(sampRepo.getEmployeeById(6)).thenReturn(emp);

		assertEquals(emp, manag.empByID(6));
	}

	@Test
	public void delEmpl() {
		Employee emp = new Employee(4, "Ram", "Chennai", "Male");
		manag.delEmp(emp.getId());
		verify(sampRepo, times(1)).deleteEmployee(emp.getId());

	}

	@Test
	public void updEmpTest() {
		Employee empl = new Employee(9, "Vijay", "Chennai", "Male");

		when(sampRepo.updateEmployee(empl)).thenReturn(empl);
		assertEquals(empl, manag.updEmp(empl));
	}

	// Exception get employee by id
	@Test
	public void getEmployeeByIdTestException() throws Exception {

		when(sampRepo.getEmployeeById(4)).thenThrow(EmployeeNotFoundException1.class);

		assertThatThrownBy(() -> manag.empByID(4)).isInstanceOf(EmployeeNotFoundException1.class);
	}

	// Exception del employee by id
	@Test
	public void delEmployeeByIdTestException() throws Exception {

		when(sampRepo.deleteEmployee(88)).thenThrow(EmployeeNotFoundException1.class);

		assertThatThrownBy(() -> manag.delEmp(88)).isInstanceOf(EmployeeNotFoundException1.class);
	}

	// Exception get all employee
	@Test
	public void getEmployeeTestException() throws Exception {

		when(sampRepo.getEmployee()).thenThrow(EmployeeNotFoundException1.class);

		assertThatThrownBy(() -> manag.getEmployee()).isInstanceOf(EmployeeNotFoundException1.class);
	}
	// Exception update  employee
	@Test
		public void updEmployeeTestException() throws Exception {
			Employee emp1 = new Employee(9, "Vijay", "Chennai", "Male");

			when(sampRepo.updateEmployee(emp1)).thenThrow(EmployeeNotFoundException1.class);

			assertThatThrownBy(() -> manag.updEmp(emp1)).isInstanceOf(EmployeeNotFoundException1.class);
		}
		
		// Exception save  employee
		@Test
		public void savEmployeeTestException() throws Exception {
			Employee emp = new Employee();
			emp.setId(6);
			emp.setName("Dhoni");
			emp.setAddress("Chennai");
			emp.setGender("Male");

			when(sampRepo.saveEmployee(emp)).thenThrow(EmployeeNotFoundException1.class);

			assertThatThrownBy(() -> manag.savEmp(emp)).isInstanceOf(EmployeeNotFoundException1.class);
		}


}