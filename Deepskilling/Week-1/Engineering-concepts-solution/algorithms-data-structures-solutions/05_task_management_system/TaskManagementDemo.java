public class TaskManagementDemo {
    public static void main(String[] args) {
        TaskLinkedList taskLinkedList = new TaskLinkedList();

        taskLinkedList.addTask(new Task(1, "Design UI", "Pending"));
        taskLinkedList.addTask(new Task(2, "Create API", "In Progress"));
        taskLinkedList.addTask(new Task(3, "Write Tests", "Pending"));

        System.out.println(taskLinkedList.searchTask(2));
        taskLinkedList.deleteTask(1);
        taskLinkedList.traverseTasks();
    }
}
