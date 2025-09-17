// adapter/PayPalAdapter.java
package adapter;

import strategy.PayPalPaymentStrategy;

public class PayPalAdapter implements PaymentAdapter {
    private PayPalPaymentStrategy paypalPayment;
    private String email;

    public PayPalAdapter(String email) {
        this.email = email;
        this.paypalPayment = new PayPalPaymentStrategy(email);
    }

    @Override
    public void pay(String amount) {
        paypalPayment.processPayment(amount);
    }
}
