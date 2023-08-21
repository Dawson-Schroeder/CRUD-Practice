package com.example.crudpractice.controllers;

import com.example.crudpractice.models.Employee;
import com.example.crudpractice.models.Manager;
import com.example.crudpractice.repositories.EmployeeRepository;
import com.example.crudpractice.repositories.ManagerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public String showPortal(Model model){
        List<Employee> employees = employeeDao.findAll();
        model.addAttribute("employees", employees);
        return "portal";
    }

    @GetMapping("/employee/{id}")
    public String showEmployee(@PathVariable long id, Model model) {
        Employee employee = employeeDao.findEmployeeById(id);
        model.addAttribute("employee", employee);
        return "inspection";
    }

    @PostMapping("/employee/update")
    public String updateEmployee(@ModelAttribute Employee employee) {
        employeeDao.save(employee);
        return "redirect:/portal";
    }

    @GetMapping("/employee/create")
    public String createEmployee(Model model){
        model.addAttribute("employee", new Employee());
        return "hired";
    }
    @PostMapping("/employee/create")
    public String submitForm(@ModelAttribute Employee employee){
        employeeDao.save(employee);
        return "redirect:/portal";
    }

    @PostMapping("/employee/{id}/delete")
    public String deleteEmployee(@PathVariable("id") long id){
        Employee employee = employeeDao.findEmployeeById(id);
        if (employee != null) {
            employeeDao.deleteById(id);
        }
        return "redirect:/portal";
    }
}
