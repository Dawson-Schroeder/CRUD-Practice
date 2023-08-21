package com.example.crudpractice.repositories;

import com.example.crudpractice.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findEmployeeById(long id);

    Employee findEmployeeByFirstName(String firstName);

    Employee findEmployeeByLastName(String lastName);

    Employee findEmployeeByEmail(String email);
}
