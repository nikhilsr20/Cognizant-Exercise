package com.cognizant.employeemanagementsystem.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeView {
    Long getId();
    String getName();
    String getEmail();
    @Value("#{target.department != null ? target.department.name : null}")
    String getDepartmentName();
}
