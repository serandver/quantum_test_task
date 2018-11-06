package com.example.demo.controller;

import com.example.demo.model.Dto;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.InitService;
import com.example.demo.service.search.HibernateSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private HibernateSearchService searchservice;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String getStartPage(Model model) {
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

    @RequestMapping(value = {"/employees"}, method = RequestMethod.POST)
    public String search(@RequestParam(value = "search", required = false) String q, Model model) {
        List<Employee> searchResults = null;
        try {
            searchResults = searchservice.fuzzySearch(q);
        } catch (Exception ex) {
            //NOP
        }
        model.addAttribute("search", searchResults);
        return "employees";
    }
}
