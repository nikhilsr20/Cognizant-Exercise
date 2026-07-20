public class TaskLinkedList {
    private TaskNode head;

    public void addTask(Task task) {
        TaskNode newNode = new TaskNode(task);

        if (head == null) {
            head = newNode;
            return;
        }

        TaskNode current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }

        current.setNext(newNode);
    }

    public Task searchTask(int taskId) {
        TaskNode current = head;

        while (current != null) {
            if (current.getTask().getTaskId() == taskId) {
                return current.getTask();
            }

            current = current.getNext();
        }

        return null;
    }

    public boolean deleteTask(int taskId) {
        if (head == null) {
            return false;
        }

        if (head.getTask().getTaskId() == taskId) {
            head = head.getNext();
            return true;
        }

        TaskNode current = head;
        while (current.getNext() != null) {
            if (current.getNext().getTask().getTaskId() == taskId) {
                current.setNext(current.getNext().getNext());
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    public void traverseTasks() {
        TaskNode current = head;

        while (current != null) {
            System.out.println(current.getTask());
            current = current.getNext();
        }
    }
}
