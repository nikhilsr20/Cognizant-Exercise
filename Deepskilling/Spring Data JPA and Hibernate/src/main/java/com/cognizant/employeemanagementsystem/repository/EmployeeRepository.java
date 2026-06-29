package com.cognizant.employeemanagementsystem.repository;

import com.cognizant.employeemanagementsystem.dto.EmployeeSummary;
import com.cognizant.employeemanagementsystem.entity.Employee;
import com.cognizant.employeemanagementsystem.projection.EmployeeView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    List<Employee> findByNameContainingIgnoreCase(String name);
    List<Employee> findByDepartmentName(String departmentName);
    List<Employee> findBySalaryGreaterThan(Double salary);
    List<Employee> findByPermanentTrue();
    List<Employee> findByDepartmentName(String departmentName, Sort sort);
    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);
    List<Employee> findPermanentEmployees();
    List<Employee> findBySalaryGreaterThanNamed(@Param("salary") Double salary);

    @Query("SELECT e FROM Employee e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(e.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Employee> searchByNameOrEmail(@Param("keyword") String keyword);

    @Query("SELECT AVG(e.salary) FROM Employee e")
    Double findAverageSalary();

    @Query("SELECT e.id AS id, e.name AS name, e.email AS email, e.department AS department FROM Employee e")
    List<EmployeeView> findEmployeeViews();

    @Query("SELECT new com.rishbootdev.employeemanagementsystem.dto.EmployeeSummary(e.id, e.name, e.email, d.name) FROM Employee e LEFT JOIN e.department d")
    List<EmployeeSummary> findEmployeeSummaries();
}
