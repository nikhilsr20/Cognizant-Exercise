package com.example.hands_on3.controller;

import com.example.hands_on3.model.Department;
import com.example.hands_on3.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {
    private  final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/departments")
    public List<Department> getAllDepartment()
    {
        return departmentService.getAllDepartment();
    }

}
