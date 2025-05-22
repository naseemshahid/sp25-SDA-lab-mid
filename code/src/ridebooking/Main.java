package ridebooking;

public class Main {
    public static void main(String[] args) {
        // Create a new booking system
        BookingSystem bookingSystem = new BookingSystem();

        // Create a test user
        User user = new User("U001", "John Doe", "john@example.com", "1234567890");

        // Test booking
        String bookingResult = bookingSystem.processBooking(user, "City Center", "Airport", "Credit Card");
        System.out.println(bookingResult);

        // Print user's booking history
        System.out.println("\nBooking History:");
        bookingSystem.getUserBookings(user.getUserId())
                    .forEach(booking -> System.out.println("Booking ID: " + booking.getBookingId() + 
                                                         ", Fare: $" + booking.getFare()));

        // Print user's payment history
        System.out.println("\nPayment History:");
        bookingSystem.paymentSystem.getUserPayments(user.getUserId())
                    .forEach(payment -> System.out.println("Transaction ID: " + payment.getTransactionId() + 
                                                         ", Amount: $" + payment.getAmount()));
    }
} 