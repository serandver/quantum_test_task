package com.example.demo.service;

import com.example.demo.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    List<Employee> findByCity (String city);
    List<Employee> findByPosition (String position);
    List<Employee> findBySalary (double salary);
}
