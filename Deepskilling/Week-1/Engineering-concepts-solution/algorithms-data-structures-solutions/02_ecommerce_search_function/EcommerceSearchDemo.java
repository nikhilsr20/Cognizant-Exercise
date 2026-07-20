import java.util.Arrays;
import java.util.Comparator;

public class EcommerceSearchDemo {
    public static void main(String[] args) {
        Product[] products = {
                new Product(1, "Mouse", "Electronics"),
                new Product(2, "Laptop", "Electronics"),
                new Product(3, "Shoes", "Fashion"),
                new Product(4, "Backpack", "Travel")
        };

        ProductSearch productSearch = new ProductSearch();
        System.out.println(productSearch.linearSearch(products, "Shoes"));

        Arrays.sort(products, Comparator.comparing(Product::getProductName, String.CASE_INSENSITIVE_ORDER));
        System.out.println(productSearch.binarySearch(products, "Laptop"));
    }
}
