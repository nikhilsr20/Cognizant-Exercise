package com.cognizant.employeemanagementsystem.repository;

import com.cognizant.employeemanagementsystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
    List<Department> findByNameContainingIgnoreCase(String name);
}
