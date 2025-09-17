import strategy.PaymentStrategy;

public class PaymentContext {
    private PaymentStrategy paymentStrategy;

    // Set the strategy dynamically
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(String amount) {
        paymentStrategy.processPayment(amount);
    }
}
