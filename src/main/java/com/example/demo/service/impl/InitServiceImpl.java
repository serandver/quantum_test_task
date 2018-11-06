package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.InitService;
import com.example.demo.service.validation.SurnameDuplicatesValidator;
import com.example.demo.service.validation.UniqueFullNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class InitServiceImpl implements InitService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private UniqueFullNameValidator fullNameValidator;

    @Autowired
    private SurnameDuplicatesValidator surnameDuplicatesValidator;

    private ThreadLocalRandom localRandom = ThreadLocalRandom.current();

    @Override
    public void generateEmployeeCollection(int quantity) {
        for (int i = 0; i < quantity; i++) {
            Employee employee = initNewEmployee();
            repository.save(employee);
        }
    }

    @Override
    public Employee initNewEmployee() {
        Employee employee = new Employee();
        setRandomSalary(employee);
        setRandomPosition(employee);
        setRandomCity(employee);
        setRandomSex(employee);
        setRandomName(employee);
        return employee;
    }

    private void setRandomSalary(Employee employee) {
        int randomSalary = localRandom.nextInt(MINIMUM_SALARY, MAXIMUM_SALARY + 1);
        employee.setSalary(randomSalary);
    }

    private void setRandomPosition(Employee employee) {
        int randomIndex = localRandom.nextInt(POSITIONS.length);
        employee.setPosition(POSITIONS[randomIndex]);
    }

    private void setRandomCity(Employee employee) {
        int randomIndex = localRandom.nextInt(CITIES.length);
        employee.setCity(CITIES[randomIndex]);
    }

    private void setRandomSex(Employee employee) {
        // woman/man ratio is 40%/60%
        int randomIndex = localRandom.nextInt(10);
        if (randomIndex < 4) {
            employee.setSex(WOMAN_SEX);
        }
        else {
            employee.setSex(MAN_SEX);
        }
    }

    private void setRandomName(Employee employee) {
        String fullName = EMPTY_STRING;
        boolean isFullNameUnique = false;
        while (!isFullNameUnique) {
            String name = initName(employee);
            String surname = initSurname();
            fullName = name + " " + surname;
            if (!fullNameValidator.validate(fullName)) {
                isFullNameUnique = true;
            }
        }
        employee.setName(fullName);
    }

    private String initName(Employee employee) {
        String name = EMPTY_STRING;

        if (employee.getSex().equals(MAN_SEX)) {
            int randomNameIndex = localRandom.nextInt(MAN_SEX.length());
            name = MEN_NAMES[randomNameIndex];
        }
        else if (employee.getSex().equals(WOMAN_SEX)) {
            int randomIndex = localRandom.nextInt(WOMAN_NAMES.length);
            name = WOMAN_NAMES[randomIndex];
        }
        return name;
    }

    private String initSurname() {
        String surname = EMPTY_STRING;
        boolean isSurnameInvalid = true;
        while (isSurnameInvalid) {
            int randomSurnameIndex = localRandom.nextInt(SURNAMES.length);
            surname = SURNAMES[randomSurnameIndex];
            if (!surnameDuplicatesValidator.validate(surname)) {
                isSurnameInvalid = false;
            }
        }
        return surname;
    }
}
