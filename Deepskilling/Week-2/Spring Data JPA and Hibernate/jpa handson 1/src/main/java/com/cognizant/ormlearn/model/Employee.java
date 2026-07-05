package com.cognizant.ormlearn.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "em_id")
    private Integer id;

    @Column(name = "em_name")
    private String name;

    @Column(name = "em_salary")
    private Double salary;

    @Column(name = "em_permanent")
    private Boolean permanent;

    @Column(name = "em_date_of_birth")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "em_dp_id")
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_skill", joinColumns = @JoinColumn(name = "es_em_id"), inverseJoinColumns = @JoinColumn(name = "es_sk_id"))
    private Set<Skill> skillList = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Boolean getPermanent() {
        return permanent;
    }

    public void setPermanent(Boolean permanent) {
        this.permanent = permanent;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(Set<Skill> skillList) {
        this.skillList = skillList;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + ", permanent=" + permanent + ", dateOfBirth=" + dateOfBirth + "}";
    }
}
