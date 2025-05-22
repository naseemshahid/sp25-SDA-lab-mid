package ridebooking;

import java.io.Serializable;

public class PaymentRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private double amount;
    private String paymentMethod;
    private long timestamp;
    private String transactionId;

    public PaymentRecord(String userId, double amount, String paymentMethod) {
        this.userId = userId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.timestamp = System.currentTimeMillis();
        this.transactionId = "TXN-" + timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getTransactionId() {
        return transactionId;
    }
} 