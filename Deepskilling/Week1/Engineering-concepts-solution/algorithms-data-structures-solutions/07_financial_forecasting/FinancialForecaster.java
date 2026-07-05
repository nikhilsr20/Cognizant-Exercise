public class FinancialForecaster {
    public double calculateFutureValue(double currentValue, double growthRate, int years) {
        if (years == 0) {
            return currentValue;
        }

        return calculateFutureValue(currentValue, growthRate, years - 1) * (1 + growthRate);
    }

    public double calculateFutureValueIterative(double currentValue, double growthRate, int years) {
        double futureValue = currentValue;

        for (int year = 1; year <= years; year++) {
            futureValue = futureValue * (1 + growthRate);
        }

        return futureValue;
    }
}
