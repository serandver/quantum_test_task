package com.example.demo.services.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.impl.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTests {

	@Mock
	private EmployeeRepository repository;

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnAllEmployees() {
		List<Employee> expectedList = Arrays.asList(
				new Employee("Иван Сидоров", "Киев", 10000, "М", "Программист"),
				new Employee("Петя Ковров", "Киев", 20000, "М", "Программист"));

		when(repository.findAll()).thenReturn(expectedList);

		List<Employee> actualList = employeeService.getAllEmployees();
		assertTrue(actualList.equals(expectedList));
	}

}
