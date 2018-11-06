package com.example.demo.model;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;

@Indexed
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Field
    private String name;
    @Field
    private String city;
    @Field
    private int salary;
    @Field
    private String sex;
    @Field
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (salary != employee.salary) return false;
        if (!name.equals(employee.name)) return false;
        if (!city.equals(employee.city)) return false;
        if (!sex.equals(employee.sex)) return false;
        return position.equals(employee.position);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + salary;
        result = 31 * result + sex.hashCode();
        result = 31 * result + position.hashCode();
        return result;
    }
}
