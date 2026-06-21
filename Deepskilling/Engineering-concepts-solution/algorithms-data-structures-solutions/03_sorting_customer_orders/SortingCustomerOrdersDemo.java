import java.util.Arrays;

public class SortingCustomerOrdersDemo {
    public static void main(String[] args) {
        Order[] bubbleOrders = {
                new Order(1, "Aman", 4500),
                new Order(2, "Riya", 1200),
                new Order(3, "Kabir", 9800),
                new Order(4, "Nisha", 6200)
        };

        Order[] quickOrders = Arrays.copyOf(bubbleOrders, bubbleOrders.length);
        OrderSorter orderSorter = new OrderSorter();

        orderSorter.bubbleSortByTotalPrice(bubbleOrders);
        orderSorter.quickSortByTotalPrice(quickOrders, 0, quickOrders.length - 1);

        System.out.println("Bubble Sort");
        for (Order order : bubbleOrders) {
            System.out.println(order);
        }

        System.out.println("Quick Sort");
        for (Order order : quickOrders) {
            System.out.println(order);
        }
    }
}
