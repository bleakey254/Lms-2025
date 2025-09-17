// adapter/CreditCardAdapter.java
package adapter;

import strategy.CreditCardPaymentStrategy;

public class CreditCardAdapter implements PaymentAdapter {
    private CreditCardPaymentStrategy creditCardPayment;
    private String cardNumber;
    private String expiry;
    private String cvv;

    public CreditCardAdapter(String cardNumber, String expiry, String cvv) {
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.cvv = cvv;
        this.creditCardPayment = new CreditCardPaymentStrategy(cardNumber, expiry, cvv);
    }

    @Override
    public void pay(String amount) {
        creditCardPayment.pay(amount);
    }
}
