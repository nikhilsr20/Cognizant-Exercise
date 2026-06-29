package com.cognizant.employeemanagementsystem.controller;

import com.cognizant.employeemanagementsystem.entity.Department;
import com.cognizant.employeemanagementsystem.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public Department create(@RequestBody Department department) {
        log.info("POST /api/departments");
        return departmentService.create(department);
    }

    @GetMapping("/{id}")
    public Department get(@PathVariable Long id) {
        log.info("GET /api/departments/{}", id);
        return departmentService.get(id);
    }

    @GetMapping
    public List<Department> findAll() {
        log.info("GET /api/departments");
        return departmentService.findAll();
    }

    @GetMapping("/search")
    public List<Department> search(@RequestParam String name) {
        log.info("GET /api/departments/search");
        return departmentService.search(name);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department department) {
        log.info("PUT /api/departments/{}", id);
        return departmentService.update(id, department);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("DELETE /api/departments/{}", id);
        departmentService.delete(id);
    }
}
