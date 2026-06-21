public class StrategyPatternTest {
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();

        paymentContext.setPaymentStrategy(new CreditCardPayment("1234567812345678"));
        paymentContext.executePayment(2500);

        paymentContext.setPaymentStrategy(new PayPalPayment("customer@example.com"));
        paymentContext.executePayment(1800);
    }
}
