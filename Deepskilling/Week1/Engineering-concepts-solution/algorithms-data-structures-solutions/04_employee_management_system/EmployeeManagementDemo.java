public class EmployeeManagementDemo {
    public static void main(String[] args) {
        EmployeeArrayManager manager = new EmployeeArrayManager(5);

        manager.addEmployee(new Employee(1, "Alice", "Manager", 70000));
        manager.addEmployee(new Employee(2, "Bob", "Developer", 60000));
        manager.addEmployee(new Employee(3, "Charu", "Analyst", 52000));

        System.out.println(manager.searchEmployee(2));
        manager.deleteEmployee(1);
        manager.traverseEmployees();
    }
}
