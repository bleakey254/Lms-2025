package observer;

import model.PaymentDetails;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceiptLogger implements PaymentObserver {
    private static final String RECEIPTS_DIR = "receipts";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

    @Override
    public void onPaymentSuccess(PaymentDetails details) {
        try {
            // Create receipts directory if it doesn't exist
            java.io.File directory = new java.io.File(RECEIPTS_DIR);
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Generate unique filename based on timestamp
            String timestamp = dateFormat.format(new Date());
            String filename = String.format("%s/receipt_%s.txt", RECEIPTS_DIR, timestamp);

            // Write receipt to file
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                writer.println("=== Payment Receipt ===");
                writer.println("Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                writer.println("Payment Type: " + details.getType());
                writer.println("Email: " + details.get("email"));
                writer.println("Amount: $" + details.get("amount"));
                writer.println("Status: Success");
                writer.println("===================");
            }

            System.out.println("🧾 Receipt logged to: " + filename);
        } catch (IOException e) {
            System.err.println("❌ Failed to log receipt: " + e.getMessage());
        }
    }
}
