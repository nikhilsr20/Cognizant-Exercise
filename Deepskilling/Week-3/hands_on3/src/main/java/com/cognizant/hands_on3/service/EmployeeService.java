package com.example.hands_on3.service;

import com.example.hands_on3.dao.EmployeeDao;
import com.example.hands_on3.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getAllEmployees()
    {
        return employeeDao.getAllEmployee();
    }
}
