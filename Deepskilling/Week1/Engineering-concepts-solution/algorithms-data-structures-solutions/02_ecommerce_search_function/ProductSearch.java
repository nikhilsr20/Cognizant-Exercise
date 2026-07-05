public class ProductSearch {
    public Product linearSearch(Product[] products, String productName) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                return product;
            }
        }

        return null;
    }

    public Product binarySearch(Product[] products, String productName) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int comparison = products[middle].getProductName().compareToIgnoreCase(productName);

            if (comparison == 0) {
                return products[middle];
            }

            if (comparison < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return null;
    }
}
