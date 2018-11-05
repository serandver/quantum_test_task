package com.example.demo.controller;

import com.example.demo.model.Dto;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.InitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private InitService InitService;

    @Autowired
    private EmployeeService employeeService;

    @Value("${welcome.message}")
    private String message;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String getStartPage(Model model) {
        model.addAttribute("message", message);
        Dto dto = new Dto();
        model.addAttribute("dto", dto);
        return "index";
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.POST)
    public String generateCollection (Model model, @ModelAttribute("dto") Dto dto) {
        int quantity = dto.getQuantity();
        InitService.generateEmployeeCollection(quantity);
        return "redirect:/employees";
    }

    @RequestMapping("/employees")
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }
}
