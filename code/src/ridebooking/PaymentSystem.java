package ridebooking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentSystem {
    private static final String PAYMENT_FILE = "payments.dat";

    public boolean processPayment(User user, double amount, String paymentMethod) {
        // In a real system, this would integrate with actual payment gateways
        try {
            // Simulate payment processing
            System.out.println("Processing payment of $" + amount + 
                             " for user " + user.getName() + 
                             " using " + paymentMethod);
            
            // Create and save payment record
            PaymentRecord record = new PaymentRecord(user.getUserId(), amount, paymentMethod);
            savePaymentRecord(record);
            
            return true;
        } catch (Exception e) {
            System.err.println("Payment processing failed: " + e.getMessage());
            return false;
        }
    }

    // Save payment record to file
    private void savePaymentRecord(PaymentRecord record) {
        List<PaymentRecord> records = getAllPaymentRecords();
        records.add(record);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PAYMENT_FILE))) {
            oos.writeObject(records);
            System.out.println("Payment record saved: $" + record.getAmount());
        } catch (IOException e) {
            System.err.println("Error saving payment record: " + e.getMessage());
        }
    }

    // Get all payment records from file
    @SuppressWarnings("unchecked")
    public List<PaymentRecord> getAllPaymentRecords() {
        List<PaymentRecord> records = new ArrayList<>();
        File file = new File(PAYMENT_FILE);
        
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PAYMENT_FILE))) {
                records = (List<PaymentRecord>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error reading payment records: " + e.getMessage());
            }
        }
        return records;
    }

    // Get user's payment history
    public List<PaymentRecord> getUserPayments(String userId) {
        return getAllPaymentRecords().stream()
                .filter(r -> r.getUserId().equals(userId))
                .toList();
    }
} 