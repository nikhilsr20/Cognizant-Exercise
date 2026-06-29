package com.cognizant.employeemanagementsystem.service;

import com.cognizant.employeemanagementsystem.dto.EmployeeRequest;
import com.cognizant.employeemanagementsystem.dto.EmployeeSummary;
import com.cognizant.employeemanagementsystem.entity.Department;
import com.cognizant.employeemanagementsystem.entity.Employee;
import com.cognizant.employeemanagementsystem.projection.EmployeeView;
import com.cognizant.employeemanagementsystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    public Employee create(EmployeeRequest request) {
        Department department = departmentService.get(request.departmentId());
        Employee employee = new Employee(null, request.name(), request.email(), request.salary(), request.permanent(), department, null, null, null, null);
        log.info("Creating employee {}", request.name());
        return employeeRepository.save(employee);
    }

    public Employee create(Employee employee) {
        log.info("Creating employee {}", employee.getName());
        return employeeRepository.save(employee);
    }

    public Employee get(Long id) {
        log.info("Fetching employee {}", id);
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public List<Employee> findAll(Sort sort) {
        log.info("Fetching sorted employees");
        return employeeRepository.findAll(sort);
    }

    public Page<Employee> findAll(Pageable pageable) {
        log.info("Fetching paged employees");
        return employeeRepository.findAll(pageable);
    }

    public Employee update(Long id, EmployeeRequest request) {
        Employee employee = get(id);
        employee.setName(request.name());
        employee.setEmail(request.email());
        employee.setSalary(request.salary());
        employee.setPermanent(request.permanent());
        employee.setDepartment(departmentService.get(request.departmentId()));
        log.info("Updating employee {}", id);
        return employeeRepository.save(employee);
    }

    public void delete(Long id) {
        log.info("Deleting employee {}", id);
        employeeRepository.deleteById(id);
    }

    public List<Employee> findByNameContaining(String name) {
        log.info("Finding employees by name {}", name);
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Employee> findByDepartmentName(String departmentName) {
        log.info("Finding employees by department {}", departmentName);
        return employeeRepository.findByDepartmentName(departmentName);
    }

    public List<Employee> searchByNameOrEmail(String keyword) {
        log.info("Searching employees by keyword {}", keyword);
        return employeeRepository.searchByNameOrEmail(keyword);
    }

    public List<Employee> findPermanentEmployees() {
        log.info("Finding permanent employees using named query");
        return employeeRepository.findPermanentEmployees();
    }

    public Double findAverageSalary() {
        log.info("Finding average salary");
        return employeeRepository.findAverageSalary();
    }

    public List<EmployeeView> findEmployeeViews() {
        log.info("Finding employee interface projections");
        return employeeRepository.findEmployeeViews();
    }

    public List<EmployeeSummary> findEmployeeSummaries() {
        log.info("Finding employee class projections");
        return employeeRepository.findEmployeeSummaries();
    }

    @Transactional
    public List<Employee> saveAll(List<Employee> employees) {
        log.info("Saving employees in batch {}", employees.size());
        return employeeRepository.saveAll(employees);
    }
}
