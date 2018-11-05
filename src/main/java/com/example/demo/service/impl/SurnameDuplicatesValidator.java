package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurnameDuplicatesValidator implements ValidatorService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public boolean validate(String string) {
        List<Employee> employees = new ArrayList<>();
        repository.findAll().forEach(employees::add);
        long count = employees.stream().filter(t -> t.getName().contains(string)).count();
        return count >= 2;
    }
}
