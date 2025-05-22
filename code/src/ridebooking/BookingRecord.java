package ridebooking;

import java.io.Serializable;

public class BookingRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bookingId;
    private String userId;
    private String source;
    private String destination;
    private double fare;
    private String paymentMethod;
    private long timestamp;

    public BookingRecord(String bookingId, String userId, String source, String destination, 
                        double fare, String paymentMethod) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.fare = fare;
        this.paymentMethod = paymentMethod;
        this.timestamp = System.currentTimeMillis();
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public double getFare() {
        return fare;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public long getTimestamp() {
        return timestamp;
    }
} 