public class OrderSorter {
    public void bubbleSortByTotalPrice(Order[] orders) {
        for (int i = 0; i < orders.length - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < orders.length - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temporary = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temporary;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    public void quickSortByTotalPrice(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSortByTotalPrice(orders, low, pivotIndex - 1);
            quickSortByTotalPrice(orders, pivotIndex + 1, high);
        }
    }

    private int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int smallerIndex = low - 1;

        for (int currentIndex = low; currentIndex < high; currentIndex++) {
            if (orders[currentIndex].getTotalPrice() <= pivot) {
                smallerIndex++;
                Order temporary = orders[smallerIndex];
                orders[smallerIndex] = orders[currentIndex];
                orders[currentIndex] = temporary;
            }
        }

        Order temporary = orders[smallerIndex + 1];
        orders[smallerIndex + 1] = orders[high];
        orders[high] = temporary;

        return smallerIndex + 1;
    }
}
