package view;

import composite.CompositeComponent;
import composite.LeafComponent;
import composite.UIComponent;
import decorator.BasicPanel;
import decorator.BorderDecorator;
import decorator.PanelComponent;
import decorator.ShadowDecorator;
import facade.PaymentFacade;
import model.PaymentDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PaymentScreen extends JPanel {
    private PaymentFacade facade;
    private JTextField cardNumberField = new JTextField();
    private JTextField cardExpiryField = new JTextField();
    private JTextField cardCVVField = new JTextField();
    private JTextField paypalEmailField = new JTextField();
    private JTextField bankAccountField = new JTextField();
    private JTextField googlePayEmailField = new JTextField();
    private JPanel dynamicFieldPanel = new JPanel();
    private ButtonGroup paymentGroup = new ButtonGroup();
    private JRadioButton cardOption = new JRadioButton("Credit Card");
    private JRadioButton paypalOption = new JRadioButton("PayPal");
    private JRadioButton bankOption = new JRadioButton("Bank Transfer");
    private JRadioButton gpayOption = new JRadioButton("Google Pay");
    private int auditorId;

    public PaymentScreen(int auditorId, PaymentFacade facade) {
        this.auditorId = auditorId;
        this.facade = facade;
        setLayout(new BorderLayout());

        add(initPaymentOptions(), BorderLayout.NORTH);
        add(decorateDynamicFieldsPanel(), BorderLayout.CENTER);
        add(initConfirmButton(), BorderLayout.SOUTH);
    }

    private JPanel initPaymentOptions() {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Select Payment Method:");
        panel.add(label);

        paymentGroup.add(cardOption);
        paymentGroup.add(paypalOption);
        paymentGroup.add(bankOption);
        paymentGroup.add(gpayOption);

        panel.add(cardOption);
        panel.add(paypalOption);
        panel.add(bankOption);
        panel.add(gpayOption);

        cardOption.addActionListener(e -> showFields("card"));
        paypalOption.addActionListener(e -> showFields("paypal"));
        bankOption.addActionListener(e -> showFields("bank"));
        gpayOption.addActionListener(e -> showFields("gpay"));

        cardOption.setSelected(true);
        showFields("card");

        return panel;
    }

    private JComponent decorateDynamicFieldsPanel() {
        dynamicFieldPanel.setLayout(new GridLayout(3, 1, 10, 10));
        dynamicFieldPanel.setOpaque(false);

        PanelComponent basePanel = new BasicPanel(dynamicFieldPanel);
        PanelComponent decorated = new ShadowDecorator(new BorderDecorator(basePanel));
        return decorated.getPanel();
    }

    private JButton initConfirmButton() {
        JButton payBtn = new JButton("✅ Confirm Payment");
        payBtn.addActionListener(this::handlePayment);
        return payBtn;
    }

    private void showFields(String type) {
        dynamicFieldPanel.removeAll();
        switch (type) {
            case "card":
                dynamicFieldPanel.add(labeledField("Card Number", cardNumberField));
                dynamicFieldPanel.add(labeledField("Expiry (MM/YY)", cardExpiryField));
                dynamicFieldPanel.add(labeledField("CVV", cardCVVField));
                break;
            case "paypal":
                dynamicFieldPanel.add(labeledField("PayPal Email", paypalEmailField));
                break;
            case "bank":
                dynamicFieldPanel.add(labeledField("Bank Account Number", bankAccountField));
                break;
            case "gpay":
                dynamicFieldPanel.add(labeledField("Google Pay Email", googlePayEmailField));
                break;
        }
        dynamicFieldPanel.revalidate();
        dynamicFieldPanel.repaint();
    }

    private JPanel labeledField(String labelText, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(labelText), BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    private void handlePayment(ActionEvent e) {
        try {
            PaymentDetails details = null;

            if (cardOption.isSelected()) {
                details = new PaymentDetails("CreditCard");
                details.put("number", cardNumberField.getText());
                details.put("expiry", cardExpiryField.getText());
                details.put("cvv", cardCVVField.getText());
            } else if (paypalOption.isSelected()) {
                details = new PaymentDetails("PayPal");
                details.put("email", paypalEmailField.getText());
            } else if (bankOption.isSelected()) {
                details = new PaymentDetails("BankTransfer");
                details.put("account", bankAccountField.getText());
            } else if (gpayOption.isSelected()) {
                details = new PaymentDetails("GooglePay");
                details.put("email", googlePayEmailField.getText());
            }

            facade.processPayment(auditorId, details);
            JOptionPane.showMessageDialog(this, "✅ Payment processed successfully.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
        }
    }
}