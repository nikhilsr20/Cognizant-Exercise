public class TaskNode {
    private final Task task;
    private TaskNode next;

    public TaskNode(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public TaskNode getNext() {
        return next;
    }

    public void setNext(TaskNode next) {
        this.next = next;
    }
}
