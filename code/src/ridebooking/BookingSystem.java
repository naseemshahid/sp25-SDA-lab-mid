package ridebooking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookingSystem {
    private static final String BOOKING_FILE = "bookings.dat";
    public final PaymentSystem paymentSystem;

    public BookingSystem() {
        this.paymentSystem = new PaymentSystem();
    }

    public String processBooking(User user, String source, String destination, String paymentMethod) {
        // Step 2: Process booking
        String bookingId = generateBookingId();
        double fare = calculateFare(source, destination);

        // Step 3 & 4: Process payment
        boolean paymentSuccess = paymentSystem.processPayment(user, fare, paymentMethod);

        // Step 5: Save booking record and return confirmation
        if (paymentSuccess) {
            BookingRecord record = new BookingRecord(bookingId, user.getUserId(), source, destination, fare, paymentMethod);
            saveBookingRecord(record);
            return String.format("Booking confirmed! Booking ID: %s, Fare: $%.2f", bookingId, fare);
        } else {
            return "Booking failed: Payment unsuccessful";
        }
    }

    private String generateBookingId() {
        return "BOOK-" + System.currentTimeMillis();
    }

    private double calculateFare(String source, String destination) {
        return 25.0;
    }

    // Save booking record to file
    private void saveBookingRecord(BookingRecord record) {
        List<BookingRecord> records = getAllBookingRecords();
        records.add(record);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKING_FILE))) {
            oos.writeObject(records);
            System.out.println("Booking record saved: " + record.getBookingId());
        } catch (IOException e) {
            System.err.println("Error saving booking record: " + e.getMessage());
        }
    }

    // Get all booking records from file
    @SuppressWarnings("unchecked")
    public List<BookingRecord> getAllBookingRecords() {
        List<BookingRecord> records = new ArrayList<>();
        File file = new File(BOOKING_FILE);
        
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOKING_FILE))) {
                records = (List<BookingRecord>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error reading booking records: " + e.getMessage());
            }
        }
        return records;
    }

    // Get user's booking history
    public List<BookingRecord> getUserBookings(String userId) {
        return getAllBookingRecords().stream()
                .filter(r -> r.getUserId().equals(userId))
                .toList();
    }
} 