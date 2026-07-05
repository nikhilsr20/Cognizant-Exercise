public class AdapterPatternTest {
    public static void main(String[] args) {
        PaymentProcessor stripeProcessor = new StripePaymentAdapter(new StripeGateway());
        PaymentProcessor payPalProcessor = new PayPalPaymentAdapter(new PayPalGateway());

        stripeProcessor.processPayment(1200.50);
        payPalProcessor.processPayment(850.75);
    }
}
