public class InventoryManagementDemo {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();

        inventoryManager.addProduct(new Product(101, "Laptop", 12, 55000));
        inventoryManager.addProduct(new Product(102, "Keyboard", 50, 1200));
        inventoryManager.addProduct(new Product(103, "Monitor", 20, 9500));

        inventoryManager.updateProduct(102, "Mechanical Keyboard", 45, 2200);
        inventoryManager.deleteProduct(103);

        for (Product product : inventoryManager.getAllProducts()) {
            System.out.println(product);
        }
    }
}
