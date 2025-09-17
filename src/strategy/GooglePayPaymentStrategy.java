package strategy;

public class GooglePayPaymentStrategy implements PaymentStrategy {
    private String email;

    public GooglePayPaymentStrategy(String email) {
        this.email = email;
    }

    @Override
    public void processPayment(String amount) {
        System.out.println("Processing Google Pay payment of $" + amount + " for email: " + email);
        // Add actual Google Pay processing logic here
    }

    public void pay(String amount) {
        processPayment(amount);
    }
}
