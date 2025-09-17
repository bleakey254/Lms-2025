package utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailUtil {
    private static final Logger logger = Logger.getLogger(EmailUtil.class.getName());

    public static void sendReceipt(String recipient, File receiptFile) {
        // Load email configuration from system properties or config file in production
        String username = System.getProperty("mail.smtp.username", "your.email@gmail.com");
        String password = System.getProperty("mail.smtp.password", "your_app_password");

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient)
            );
            message.setSubject("✅ Your LMS Payment Receipt");

            // Create attachment
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(receiptFile);

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Attached is your payment receipt. Thank you for using LMS!");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachment);

            message.setContent(multipart);

            // Send the email
            Transport.send(message);

            logger.info("Receipt email sent successfully to: " + recipient);
        } catch (MessagingException e) {
            logger.log(Level.SEVERE, "Failed to send receipt email", e);
            throw new RuntimeException("Failed to send receipt email: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error while sending receipt email", e);
            throw new RuntimeException("Unexpected error while sending receipt email: " + e.getMessage(), e);
        }
    }
}
