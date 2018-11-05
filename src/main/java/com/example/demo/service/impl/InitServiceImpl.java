package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.InitService;
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
        Employee employee = new Employee();
        for (int i = 0; i < quantity; i++) {
            employee = initEmployee(employee);
            repository.save(employee);
        }
    }

    @Override
    public Employee initEmployee(Employee employee) {
        setRandomSalary(employee);
        setRandomPosition(employee);
        setRandomCity(employee);
        setRandomSex(employee);
        setName(employee);
        return employee;
    }

    private void setName(Employee employee) {
        String fullName = "";
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
            int randomNameIndex = localRandom.nextInt(5);
            name = MEN_NAMES[randomNameIndex];
        }
        else if (employee.getSex().equals(WOMAN_SEX)) {
            int randomIndex = localRandom.nextInt(5);
            name = WOMAN_NAMES[randomIndex];
        }
        return name;
    }

    private String initSurname() {
        String surname = "";
        boolean isSurnameInvalid = true;
        while (isSurnameInvalid) {
            int randomSurnameIndex = localRandom.nextInt(9);
            surname = SURNAMES[randomSurnameIndex];
            if (!surnameDuplicatesValidator.validate(surname)) {
                isSurnameInvalid = false;
            }
        }
        return surname;
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

    private void setRandomCity(Employee employee) {
        int randomIndex = localRandom.nextInt(0, CITIES.length);
        employee.setCity(CITIES[randomIndex]);
    }

    private void setRandomPosition(Employee employee) {
        int randomIndex = localRandom.nextInt(0, POSITIONS.length);
        employee.setPosition(POSITIONS[randomIndex]);
    }

    private void setRandomSalary(Employee employee) {
        int randomSalary = localRandom.nextInt(MINIMUM_SALARY, MAXIMUM_SALARY + 1);
        employee.setSalary(Math.round(randomSalary));
    }
}
