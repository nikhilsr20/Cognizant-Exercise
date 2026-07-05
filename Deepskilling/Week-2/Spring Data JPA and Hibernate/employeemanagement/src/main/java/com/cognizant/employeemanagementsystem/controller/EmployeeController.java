package com.cognizant.employeemanagementsystem.controller;

import com.cognizant.employeemanagementsystem.dto.EmployeeRequest;
import com.cognizant.employeemanagementsystem.dto.EmployeeSummary;
import com.cognizant.employeemanagementsystem.entity.Employee;
import com.cognizant.employeemanagementsystem.projection.EmployeeView;
import com.cognizant.employeemanagementsystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;



    @PostMapping
    public Employee create(@RequestBody EmployeeRequest request) {
        log.info("POST /api/employees");
        return employeeService.create(request);
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable Long id) {
        log.info("GET /api/employees/{}", id);
        return employeeService.get(id);
    }

    @GetMapping
    public Page<Employee> findAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String sortBy,
                                  @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        log.info("GET /api/employees");
        return employeeService.findAll(PageRequest.of(page, size, sort));
    }

    @GetMapping("/name")
    public List<Employee> findByName(@RequestParam String name) {
        log.info("GET /api/employees/name");
        return employeeService.findByNameContaining(name);
    }

    @GetMapping("/department")
    public List<Employee> findByDepartment(@RequestParam String name) {
        log.info("GET /api/employees/department");
        return employeeService.findByDepartmentName(name);
    }

    @GetMapping("/search")
    public List<Employee> search(@RequestParam String keyword) {
        log.info("GET /api/employees/search");
        return employeeService.searchByNameOrEmail(keyword);
    }

    @GetMapping("/permanent")
    public List<Employee> permanentEmployees() {
        log.info("GET /api/employees/permanent");
        return employeeService.findPermanentEmployees();
    }

    @GetMapping("/average-salary")
    public Double averageSalary() {
        log.info("GET /api/employees/average-salary");
        return employeeService.findAverageSalary();
    }

    @GetMapping("/projection/interface")
    public List<EmployeeView> interfaceProjection() {
        log.info("GET /api/employees/projection/interface");
        return employeeService.findEmployeeViews();
    }

    @GetMapping("/projection/class")
    public List<EmployeeSummary> classProjection() {
        log.info("GET /api/employees/projection/class");
        return employeeService.findEmployeeSummaries();
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id,
                           @RequestBody EmployeeRequest request) {
        log.info("PUT /api/employees/{}", id);
        return employeeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("DELETE /api/employees/{}", id);
        employeeService.delete(id);
    }
}