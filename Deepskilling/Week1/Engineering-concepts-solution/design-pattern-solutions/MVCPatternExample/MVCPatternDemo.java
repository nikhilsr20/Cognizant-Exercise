public class MVCPatternDemo {
    public static void main(String[] args) {
        Student student = new Student("Aman", 101, "B");
        StudentView studentView = new StudentView();
        StudentController studentController = new StudentController(student, studentView);

        studentController.updateView();

        studentController.setStudentName("Aman Sharma");
        studentController.setStudentGrade("A");
        studentController.updateView();
    }
}
