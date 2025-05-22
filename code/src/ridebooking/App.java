package ridebooking;

import java.util.List;

public class App {
    private BookingSystem bookingSystem;
    private User currentUser;

    public App() {
        this.bookingSystem = new BookingSystem();
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        user.saveToFile(); // Save user data to file
    }

    public String bookRide(String source, String destination, String paymentMethod) {
        if (currentUser == null) {
            return "Error: No user logged in";
        }

        // Step 1: Process the booking request
        String bookingResult = bookingSystem.processBooking(currentUser, source, destination, paymentMethod);
        
        // Step 6: Notify user
        notifyUser(bookingResult);
        
        return bookingResult;
    }

    private void notifyUser(String message) {
        // In a real application, this could send push notifications, SMS, or emails
        System.out.println("Notification to " + currentUser.getName() + ": " + message);
    }

    // Get booking history for current user
    public List<BookingRecord> getCurrentUserBookings() {
        if (currentUser == null) {
            throw new IllegalStateException("No user logged in");
        }
        return bookingSystem.getUserBookings(currentUser.getUserId());
    }

    // Get payment history for current user
    public List<PaymentRecord> getCurrentUserPayments() {
        if (currentUser == null) {
            throw new IllegalStateException("No user logged in");
        }
        return bookingSystem.paymentSystem.getUserPayments(currentUser.getUserId());
    }
} 