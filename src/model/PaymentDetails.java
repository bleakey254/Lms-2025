package model;

import utils.CryptoUtils;

import java.util.HashMap;
import java.util.Map;

public class PaymentDetails {
    private final String type;
    private final Map<String, String> encryptedData = new HashMap<>();

    public PaymentDetails(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void put(String key, String value) {
        encryptedData.put(key, CryptoUtils.encrypt(value)); // 🔐 Store encrypted
    }

    public String get(String key) {
        String enc = encryptedData.get(key);
        return enc != null ? CryptoUtils.decrypt(enc) : null; // 🔓 Decrypt on access
    }

    public String getMaskedInfo() {
        switch (type) {
            case "CreditCard":
                String card = get("number");
                return "Card ending in " + (card != null && card.length() > 4 ? card.substring(card.length() - 4) : "XXXX");
            case "PayPal":
            case "GooglePay":
                return "Email: " + get("email");
            case "BankTransfer":
                return "Bank Account: " + get("account");
            default:
                return "Unknown Payment";
        }
    }
}
