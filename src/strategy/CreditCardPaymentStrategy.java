package strategy;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    private String cardNumber;
    private String expiry;
    private String cvv;

    public CreditCardPaymentStrategy(String cardNumber, String expiry, String cvv) {
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.cvv = cvv;
    }

    public void pay(String amount) {
        processPayment(amount);
    }

    @Override
    public void processPayment(String amount) {
        System.out.println("Processing Credit Card payment of $" + amount +
            " with card: " + cardNumber + ", expiry: " + expiry + ", cvv: " + cvv);
        // Add actual credit card processing logic here
    }
}
