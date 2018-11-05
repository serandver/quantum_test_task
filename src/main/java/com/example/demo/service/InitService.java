package com.example.demo.service;

import com.example.demo.model.Employee;

public interface InitService {
    String[] MEN_NAMES = new String[]{"Вова", "Гриша", "Федя", "Петя", "Трофим"};
    String[] WOMAN_NAMES = new String[]{"Гюльчатай", "Акстисья", "Фрося", "Галя", "Василиса"};
    String[] SURNAMES = new String[]{"Иванов", "Петров", "Сидоров", "Иваненко", "Петренко", "Сидоренко", "Фридман", "Гоцман", "Шварцман"};
    String[] CITIES = new String[]{"Киев", "Львов", "Днепр", "Харьков"};
    String[] POSITIONS = new String[]{"Секретарь", "Менеджер", "Директор", "Грузчик"};
    String MAN_SEX = "М";
    String WOMAN_SEX = "Ж";
    int MINIMUM_SALARY = 5000;
    int MAXIMUM_SALARY = 15000;
    String EMPTY_STRING = "";

    Employee initEmployee(Employee employee);
    void generateEmployeeCollection(int quantity);
}
