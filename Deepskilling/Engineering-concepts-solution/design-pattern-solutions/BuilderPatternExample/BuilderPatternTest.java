public class BuilderPatternTest {
    public static void main(String[] args) {
        Computer officeComputer = new Computer.Builder()
                .setCpu("Intel i5")
                .setRam("16 GB")
                .setStorage("512 GB SSD")
                .build();

        Computer gamingComputer = new Computer.Builder()
                .setCpu("AMD Ryzen 9")
                .setRam("32 GB")
                .setStorage("2 TB SSD")
                .setGraphicsCard("NVIDIA RTX 4070")
                .setBluetoothEnabled(true)
                .build();

        System.out.println(officeComputer);
        System.out.println(gamingComputer);
    }
}
