package com.example.crudpractice.repositories;

import com.example.crudpractice.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager , Long> {
    Manager findManagerById(long id);
    Manager findManagerByEmail(String email);
}
