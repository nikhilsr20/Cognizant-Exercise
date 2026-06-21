public class ProxyPatternTest {
    public static void main(String[] args) {
        Image image = new ProxyImage("profile-photo.png");

        image.display();
        image.display();
    }
}
