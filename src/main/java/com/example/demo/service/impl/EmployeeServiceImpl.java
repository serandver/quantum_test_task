package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        Iterable<Employee> repositoryAll = repository.findAll();
        for (Employee employee : repositoryAll) {
            employees.add(employee);
        }
        return employees;
    }

    @Override
    public List<Employee> findByCity(String city) {
        return getAllEmployees().stream()
                .filter(t -> t.getCity().equals(city))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> findByPosition(String position) {
        return getAllEmployees().stream()
                .filter(t -> t.getPosition().equals(position))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> findBySalary(double salary) {
        return getAllEmployees().stream()
                .filter(t -> (t.getSalary() > salary))
                .collect(Collectors.toList());
    }
}
