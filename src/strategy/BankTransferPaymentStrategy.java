package strategy;

public class BankTransferPaymentStrategy implements PaymentStrategy {
    private String accountNumber;

    public BankTransferPaymentStrategy(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public void processPayment(String amount) {
        System.out.println("Processing Bank Transfer payment of $" + amount + " for account: " + accountNumber);
        // Add actual bank transfer processing logic here
    }

    public void pay(String amount) {
        processPayment(amount);
    }
}
