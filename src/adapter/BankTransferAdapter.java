// adapter/BankTransferAdapter.java
package adapter;

import strategy.BankTransferPaymentStrategy;

public class BankTransferAdapter implements PaymentAdapter {
    private BankTransferPaymentStrategy bankPayment;
    private String accountNumber;

    public BankTransferAdapter(String accountNumber) {
        this.accountNumber = accountNumber;
        this.bankPayment = new BankTransferPaymentStrategy(accountNumber);
    }

    @Override
    public void pay(String amount) {
        bankPayment.pay(amount);
    }
}
