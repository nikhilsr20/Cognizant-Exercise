package com.cognizant.employeemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
@NamedQueries({
        @NamedQuery(
                name = "Employee.findPermanentEmployees",
                query = "SELECT e FROM Employee e WHERE e.permanent = true"
        ),
        @NamedQuery(
                name = "Employee.findBySalaryGreaterThanNamed",
                query = "SELECT e FROM Employee e WHERE e.salary > :salary"
        )
})
@EntityListeners(AuditingEntityListener.class)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Double salary;

    @Column(nullable = false)
    private Boolean permanent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}