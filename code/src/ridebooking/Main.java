package ridebooking;

public class Main {
    public static void main(String[] args) {
        try {
            // Create a new booking system
            BookingSystem bookingSystem = new BookingSystem();

            // Create a test user
            User user = new User("U001", "John Doe", "john@example.com", "1234567890");
            user.saveToFile();
            System.out.println("User created: " + user.getName());

            // Test booking with valid parameters
            String bookingResult = bookingSystem.processBooking(user, "City Center", "Airport", "Credit Card");
            System.out.println("\nBooking Test 1:");
            System.out.println(bookingResult);

            // Test booking with invalid parameters (should fail gracefully)
            System.out.println("\nBooking Test 2 (Invalid Parameters):");
            System.out.println(bookingSystem.processBooking(null, "City Center", "Airport", "Credit Card"));

            // Print user's booking history
            System.out.println("\nBooking History:");
            var bookings = bookingSystem.getUserBookings(user.getUserId());
            if (bookings.isEmpty()) {
                System.out.println("No booking history found");
            } else {
                bookings.forEach(booking -> System.out.println(
                    String.format("Booking ID: %s, From: %s, To: %s, Fare: $%.2f", 
                        booking.getBookingId(), 
                        booking.getSource(),
                        booking.getDestination(),
                        booking.getFare())
                ));
            }

            // Print user's payment history
            System.out.println("\nPayment History:");
            var payments = bookingSystem.paymentSystem.getUserPayments(user.getUserId());
            if (payments.isEmpty()) {
                System.out.println("No payment history found");
            } else {
                payments.forEach(payment -> System.out.println(
                    String.format("Transaction ID: %s, Amount: $%.2f, Method: %s", 
                        payment.getTransactionId(),
                        payment.getAmount(),
                        payment.getPaymentMethod())
                ));
            }

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 