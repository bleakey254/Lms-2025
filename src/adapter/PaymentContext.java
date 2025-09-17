// adapter/PaymentContext.java
package adapter;

public class PaymentContext {
    private PaymentAdapter adapter;

    public void setPaymentAdapter(PaymentAdapter adapter) {
        this.adapter = adapter;
    }

    public void processPayment(String amount) {
        if (adapter != null) {
            adapter.pay(amount);
        } else {
            throw new IllegalStateException("No payment adapter selected");
        }
    }
}

