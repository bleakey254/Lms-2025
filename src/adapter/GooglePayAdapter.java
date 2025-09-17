// adapter/GooglePayAdapter.java
package adapter;

import strategy.GooglePayPaymentStrategy;

public class GooglePayAdapter implements PaymentAdapter {
    private GooglePayPaymentStrategy googlePay;
    private String email;

    public GooglePayAdapter(String email) {
        this.email = email;
        this.googlePay = new GooglePayPaymentStrategy(email);
    }

    @Override
    public void pay(String amount) {
        googlePay.pay(amount);
    }
}
