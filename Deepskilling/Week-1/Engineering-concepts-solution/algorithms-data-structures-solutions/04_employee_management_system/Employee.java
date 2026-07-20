public class Employee {
    private final int employeeId;
    private final String name;
    private final String position;
    private final double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString() {
        return "Employee{id=" + employeeId + ", name='" + name + "', position='" + position + "', salary=" + salary + "}";
    }
}
