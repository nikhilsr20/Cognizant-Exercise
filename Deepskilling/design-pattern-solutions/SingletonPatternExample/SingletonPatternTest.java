public class SingletonPatternTest {
    public static void main(String[] args) {
        Logger firstLogger = Logger.getInstance();
        Logger secondLogger = Logger.getInstance();

        firstLogger.log("Application started");
        secondLogger.log("User logged in");

        System.out.println("Same instance: " + (firstLogger == secondLogger));
    }
}
