package observer;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import model.User;

import java.util.Properties;

public class EmailNotificationObserver implements Observer {

    private final User user;

    public EmailNotificationObserver(User user) {
        this.user = user;
    }

    @Override
    public void update(Object eventData) {
        if (eventData instanceof String message) {
            sendEmail(user.getEmail(), "LMS Notification", message);
        }
    }

    private void sendEmail(String recipient, String subject, String content) {
        final String fromEmail = "your-email@gmail.com";       // Replace with your Gmail
        final String password = "your-app-password";           // Use an App Password

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
            System.out.println("Email sent to " + recipient);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
