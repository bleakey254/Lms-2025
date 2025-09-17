package observer;

import model.PaymentDetails;

public class EmailNotifier implements PaymentObserver {
    @Override
    public void onPaymentSuccess(PaymentDetails details) {
        System.out.println("📧 Sending payment confirmation email...");
        System.out.println("To: " + getEmail(details));
        System.out.println("Subject: Payment Confirmation");
        System.out.println("Body: Your payment using " + details.getType() + " was successful.");
    }

    private String getEmail(PaymentDetails details) {
        return switch (details.getType()) {
            case "PayPal" -> details.get("email");
            case "GooglePay" -> details.get("email");
            case "CreditCard" -> details.get("email");
            default -> "customer@example.com";
        };
    }
}
