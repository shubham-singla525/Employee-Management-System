package com.luv2code.springboot.thymeleafdemo.controller;


import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String getAllEmployees(Model theModel){
       List<Employee> employeeList = employeeService.findAll();
       theModel.addAttribute("employees",employeeList);
       return "employees/list-employees";
    }

    @GetMapping("/add")
     public String saveEmployee(Model theModel){
        theModel.addAttribute("employee", new Employee());
        return "employees/save-employee";
    }



    @GetMapping("/update")
    public String updateEmployee(@RequestParam("employeeId") int id, Model theModel){
        Employee employee = employeeService.findById(id);
        theModel.addAttribute("employee",employee);
        return "employees/save-employee";
    }


    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.save(employee);
        return "redirect:employees/list";

    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id, Model theModel){
        employeeService.deleteById(id);
        return "redirect:employees/list";
    }



}
