package com.example.hands_on3.service;

import com.example.hands_on3.dao.DepartmentDao;
import com.example.hands_on3.model.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentDao departmentDao;


    public DepartmentService(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public List<Department> getAllDepartment()
    {
        return  departmentDao.getAllDepartment();
    }
}
