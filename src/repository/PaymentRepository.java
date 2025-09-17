package repository;

import model.PaymentDetails;
import javax.swing.table.DefaultTableModel;

// Interface for payment data operations (Repository Pattern)
public interface PaymentRepository {
    DefaultTableModel getPaymentTableModel(int auditorId); // For populating JTable with payments
    void processPayment(int auditorId, PaymentDetails details);
}