package com.example.hands_on3.model;

import java.util.List;

public class Employee {
    private int id;
    private String name;
    private double salary;
    private boolean permanent;

    private Department department;

    private List<Skill> skills;

    public Employee() {
    }

    public Employee(int id, String name, boolean permanent, double salary, List<Skill> skills, Department department) {
        this.id = id;
        this.name = name;
        this.permanent = permanent;
        this.salary = salary;
        this.skills = skills;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
