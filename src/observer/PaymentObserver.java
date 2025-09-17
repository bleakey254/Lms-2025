package observer;

import model.PaymentDetails;

public interface PaymentObserver {
    void onPaymentSuccess(PaymentDetails details);
}
