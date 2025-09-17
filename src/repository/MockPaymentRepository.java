package repository;

import model.PaymentDetails;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

// Mock payment data class
class Payment {
    private int id;
    private int auditorId;
    private String method;
    private String details;
    private String status;

    public Payment(int id, int auditorId, String method, String details, String status) {
        this.id = id;
        this.auditorId = auditorId;
        this.method = method;
        this.details = details;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getAuditorId() {
        return auditorId;
    }

    public String getMethod() {
        return method;
    }

    public String getDetails() {
        return details;
    }

    public String getStatus() {
        return status;
    }
}

// Mock implementation of PaymentRepository
public class MockPaymentRepository implements PaymentRepository {
    private List<Payment> payments;
    private int nextId;

    public MockPaymentRepository() {
        this.payments = new ArrayList<>();
        this.nextId = 1;
        // Initialize mock data
        payments.add(new Payment(nextId++, 0, "CreditCard", "****1234, Exp: 12/25, CVV: ***", "Completed"));
        payments.add(new Payment(nextId++, 0, "PayPal", "user@example.com", "Pending"));
    }

    @Override
    public DefaultTableModel getPaymentTableModel(int auditorId) {
        String[] columns = {"ID", "Method", "Details", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Payment payment : payments) {
            if (payment.getAuditorId() == auditorId) {
                model.addRow(new Object[]{payment.getId(), payment.getMethod(), payment.getDetails(), payment.getStatus()});
            }
        }
        return model;
    }

    @Override
    public void processPayment(int auditorId, PaymentDetails details) {
        Payment payment = new Payment(
            nextId++,
            auditorId,
            details.getType(),
            "Payment processed securely",  // We don't expose the encrypted details
            "Processed"
        );
        payments.add(payment);
    }
}