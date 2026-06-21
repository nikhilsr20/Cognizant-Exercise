public class ObserverPatternTest {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();
        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();

        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);
        stockMarket.setStockPrice("TCS", 4120.75);

        stockMarket.deregisterObserver(webApp);
        stockMarket.setStockPrice("INFY", 1560.40);
    }
}
