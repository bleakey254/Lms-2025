package proxy;

import model.PaymentDetails;

import java.util.logging.Logger;

public class RealPaymentProcessor implements PaymentProcessor {
    private static final Logger logger = Logger.getLogger(RealPaymentProcessor.class.getName());

    @Override
    public String process(PaymentDetails details) {
        // Simulate real payment logic
        try {
            Thread.sleep(1000); // simulate processing time
            logger.info("💳 Payment processed for: " + details.getMaskedInfo());
            return "✅ Payment successful via " + details.getType();
        } catch (InterruptedException e) {
            logger.severe("⚠️ Payment processing interrupted: " + e.getMessage());
            return "❌ Payment failed due to system error.";
        }
    }
}
