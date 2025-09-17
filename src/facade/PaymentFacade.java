package facade;

import model.PaymentDetails;
import repository.PaymentRepository;
import javax.swing.table.DefaultTableModel;

public class PaymentFacade {
    private PaymentRepository paymentRepository;

    public PaymentFacade(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public DefaultTableModel getPaymentTableModel(int auditorId) {
        return paymentRepository.getPaymentTableModel(auditorId);
    }

    public void processPayment(int auditorId, PaymentDetails details) {
        paymentRepository.processPayment(auditorId, details);
    }
}