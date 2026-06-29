package com.cognizant.employeemanagementsystem.service;

import com.cognizant.employeemanagementsystem.entity.Department;
import com.cognizant.employeemanagementsystem.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department create(Department department) {
        log.info("Creating department {}", department.getName());
        return departmentRepository.save(department);
    }

    public Department get(Long id) {
        log.info("Fetching department {}", id);
        return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public List<Department> findAll() {
        log.info("Fetching all departments");
        return departmentRepository.findAll(Sort.by("name"));
    }

    public Department update(Long id, Department department) {
        Department existing = get(id);
        existing.setName(department.getName());
        log.info("Updating department {}", id);
        return departmentRepository.save(existing);
    }

    public void delete(Long id) {
        log.info("Deleting department {}", id);
        departmentRepository.deleteById(id);
    }

    public List<Department> search(String name) {
        log.info("Searching departments by {}", name);
        return departmentRepository.findByNameContainingIgnoreCase(name);
    }
}
