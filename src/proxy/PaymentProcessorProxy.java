package proxy;

import model.PaymentDetails;
import observer.PaymentObserver;
import utils.EncryptionUtil;
import utils.ReceiptGenerator;
import utils.EmailUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class PaymentProcessorProxy implements PaymentProcessor {
    private final PaymentProcessor realProcessor = new RealPaymentProcessor();
    private static final Logger logger = Logger.getLogger(PaymentProcessorProxy.class.getName());

    private final List<PaymentObserver> observers = new ArrayList<>();

    @Override
    public String process(PaymentDetails details) {
        logger.info("🔍 Attempting to process payment: " + details.getMaskedInfo());

        // ✅ Validate input
        if (!isValid(details)) {
            logger.warning("❌ Validation failed for: " + details.getMaskedInfo());
            return "❌ Payment validation failed. Please check your input.";
        }

        // 🔐 Encrypt sensitive fields before processing
        encryptSensitiveFields(details);

        // 🔐 Simulated access check
        if (!hasPermission()) {
            logger.warning("🚫 Access denied for processing payment.");
            return "🚫 You are not authorized to perform this payment.";
        }

        // 💳 Delegate to real processor
        String result = realProcessor.process(details);

        // 📬 Notify, Generate PDF & Email
        if (result.contains("successful")) {
            notifyObservers(details);
            handlePostPaymentActions(details);
        }

        return result;
    }

    private boolean isValid(PaymentDetails details) {
        return switch (details.getType()) {
            case "CreditCard" -> Pattern.matches("\\d{16}", details.get("number")) &&
                    Pattern.matches("\\d{2}/\\d{2}", details.get("expiry")) &&
                    Pattern.matches("\\d{3}", details.get("cvv"));
            case "PayPal", "GooglePay" ->
                Pattern.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", details.get("email"));
            case "BankTransfer" ->
                details.get("account") != null && !details.get("account").isBlank();
            default -> false;
        };
    }

    private void encryptSensitiveFields(PaymentDetails details) {
        switch (details.getType()) {
            case "CreditCard":
                details.put("number", EncryptionUtil.encrypt(details.get("number")));
                details.put("cvv", EncryptionUtil.encrypt(details.get("cvv")));
                break;
            case "PayPal":
            case "GooglePay":
                details.put("email", EncryptionUtil.encrypt(details.get("email")));
                break;
            case "BankTransfer":
                details.put("account", EncryptionUtil.encrypt(details.get("account")));
                break;
        }
    }

    private boolean hasPermission() {
        // 🔐 Replace with real session or user permission logic
        return true;
    }

    private void handlePostPaymentActions(PaymentDetails details) {
        try {
            // 🧾 Generate receipt PDF
            File receipt = ReceiptGenerator.generate(details);
            if (receipt != null) {
                logger.info("📄 Receipt generated at: " + receipt.getAbsolutePath());

                // 📥 Email receipt
                String email = switch (details.getType()) {
                    case "CreditCard" -> "admin@lms.com";
                    case "PayPal", "GooglePay", "BankTransfer" -> EncryptionUtil.decrypt(details.get("email"));
                    default -> throw new IllegalStateException("Unexpected payment type: " + details.getType());
                };

                EmailUtil.sendReceipt(email, receipt);
                logger.info("📧 Receipt emailed to: " + email);
            } else {
                logger.warning("⚠️ Failed to generate receipt");
            }
        } catch (Exception e) {
            logger.warning("⚠️ Post-payment actions failed: " + e.getMessage());
        }
    }

    // ===============================
    // 🔁 Observer Pattern Support
    // ===============================

    public void addObserver(PaymentObserver observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    private void notifyObservers(PaymentDetails details) {
        for (PaymentObserver observer : observers) {
            try {
                if (observer != null) {
                    observer.onPaymentSuccess(details);
                }
            } catch (Exception e) {
                logger.warning("⚠️ Observer failed: " + e.getMessage());
            }
        }
    }
}
