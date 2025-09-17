package proxy;

import model.PaymentDetails;

public interface PaymentProcessor {
    String process(PaymentDetails details);
}
