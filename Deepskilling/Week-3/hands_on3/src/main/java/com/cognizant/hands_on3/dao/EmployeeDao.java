package com.example.hands_on3.dao;

import com.example.hands_on3.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDao {
    private static List<Employee> EMPLOYEE_LIST;

    public EmployeeDao(ApplicationContext applicationContext) {
        EMPLOYEE_LIST = (List<Employee>) applicationContext.getBean("employeeList");
    }

    public List<Employee> getAllEmployee()
    {
        return EMPLOYEE_LIST;
    }
}
