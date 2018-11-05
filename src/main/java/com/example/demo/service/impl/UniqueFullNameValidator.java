package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UniqueFullNameValidator implements ValidatorService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public boolean validate(String string) {
        List<Employee> employees = new ArrayList<>();
        repository.findAll().forEach(employees::add);
        Optional<Employee> employee = employees.stream()
                .filter(t -> t.getName().equals(string))
                .findFirst();
        return employee.isPresent();
    }
}
