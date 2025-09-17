package strategy;

public class PayPalPaymentStrategy implements PaymentStrategy {
    private String email;

    public PayPalPaymentStrategy(String email) {
        this.email = email;
    }

    @Override
    public void processPayment(String amount) {
        System.out.println("Processing PayPal payment of $" + amount + " for email: " + email);
        // Add actual PayPal processing logic here
    }
}
