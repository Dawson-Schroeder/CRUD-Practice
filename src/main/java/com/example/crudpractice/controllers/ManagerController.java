package com.example.crudpractice.controllers;

import com.example.crudpractice.models.Employee;
import com.example.crudpractice.models.Manager;
import com.example.crudpractice.repositories.EmployeeRepository;
import com.example.crudpractice.repositories.ManagerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class ManagerController {

    private ManagerRepository managerDao;

    private EmployeeRepository employeeDao;

    public ManagerController(ManagerRepository managerDao, EmployeeRepository employeeDao) {
        this.managerDao = managerDao;
        this.employeeDao = employeeDao;
    }

    @GetMapping("/")
    public String showHome(){
        return "home";
    }

    @PostMapping("/login")
    public String managerLogin(@RequestParam(name="email") String email){

        Manager exsistingManager = managerDao.findManagerByEmail(email);

        if (exsistingManager != null && Objects.equals(exsistingManager.getEmail(), email)){
            return "redirect:/portal";
        }
        return "/home";
    }

    @GetMapping("/portal")
    public String showPortal(){
        return "portal";
    }





}
