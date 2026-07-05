public class FinancialForecastingDemo {
    public static void main(String[] args) {
        FinancialForecaster forecaster = new FinancialForecaster();

        double currentValue = 100000;
        double growthRate = 0.08;
        int years = 5;

        System.out.println(forecaster.calculateFutureValue(currentValue, growthRate, years));
        System.out.println(forecaster.calculateFutureValueIterative(currentValue, growthRate, years));
    }
}
