package com.example.demo.model;

import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Employee {

    @Id
    private String name;
    private String city;
    private int salary;
    private String sex;
    private String position;

    public Employee() {
    }

    public Employee(String name, String city, int salary, String sex, String position) {
        this.name = name;
        this.city = city;
        this.salary = salary;
        this.sex = sex;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
