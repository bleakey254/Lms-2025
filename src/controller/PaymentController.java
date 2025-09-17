package controller;

import model.PaymentDetails;
import observer.EmailNotifier;
import observer.PaymentObserver;
import observer.ReceiptLogger;
import proxy.PaymentProcessor;
import proxy.PaymentProcessorProxy;

public class PaymentController {
    private final PaymentProcessorProxy processor;

    public PaymentController() {
        // 🔐 Use secure proxy
        this.processor = new PaymentProcessorProxy();

        // ✅ Register observers
        this.processor.addObserver(new ReceiptLogger());   // 🧾 Logs receipt to file
        this.processor.addObserver(new EmailNotifier());   // 📧 Simulates sending email
    }

    public String processPayment(PaymentDetails details) {
        if (details == null || details.getType() == null) {
            return "❌ Invalid payment details.";
        }

        return processor.process(details);
    }
}
