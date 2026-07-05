package com.example.hands_on3.dao;

import com.example.hands_on3.model.Department;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDao {
    private static List<Department> DEPARTMENT_LIST;

    public DepartmentDao(ApplicationContext applicationContext) {
        DEPARTMENT_LIST = (List<Department>) applicationContext.getBean("departmentList");
    }

    public List<Department> getAllDepartment()
    {
        return DEPARTMENT_LIST;
    }
}
